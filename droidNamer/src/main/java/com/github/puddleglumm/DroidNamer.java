package com.github.puddleglumm;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DroidNamer {

    private static final int MAX_NAME_TOKENS = 3;
    private static final String NICKNAME_SEPARATOR = "-";

    public static void main(String[] args) {
        String modelName = "Homework Processor";
        String serialNumber = "A643D2";
        // expected output: HP-D2

        System.out.println("Droid model " + modelName + " with serial number " + serialNumber +
                            " has nickname " + abbreviateDroidName(modelName, serialNumber));
    }

    public static String abbreviateDroidName(String modelName, String serialNumber) {
        return droidNameAbbreviatorPipeline.apply(modelName, serialNumber);
    }

    private static final BiFunction< String, String, PipelineData> instantiatePipelineData = PipelineData::new;

    // Model: Bodacious Blaster Bot XL Mark V | Serial: A643D2 -> BBBXMV-A643D2
    private static final Function<PipelineData, PipelineData> convertModelToInitials = pinelineData -> {
        String[] modelNameWords = pinelineData.getModel().split(" ");
        StringBuilder modelNameAbbreviation = new StringBuilder();
        for( String word : modelNameWords) {
            modelNameAbbreviation.append(word.charAt(0));
        }

        return new PipelineData(modelNameAbbreviation.toString(), pinelineData.getSerialNumber());
    };

    // BBBXMV-A643D2 -> B3XMV-A643D2
    private static final Function<PipelineData, PipelineData> convertRepeatInitialsToNumber = pinelineData -> {
        Pattern repeatingCharsPattern = Pattern.compile("(\\w)\\1+");
        String model = pinelineData.getModel();
        Matcher matcher = repeatingCharsPattern.matcher(model);
        StringBuilder convertedModelStringBuilder = new StringBuilder();
        int currentIndex = 0;
        while (matcher.find()) {
            // Take everything from current index through the 1st character of the match
            convertedModelStringBuilder.append(model, currentIndex, matcher.start() + 1);

            // Add a number which represents the number of repeating characters
            convertedModelStringBuilder.append(matcher.group().length());

            // resume at the next character after the last repeating character
            currentIndex = matcher.end();
        }
        convertedModelStringBuilder.append(model.substring(currentIndex)); // Get leftover chars after all matches found

        return new PipelineData(convertedModelStringBuilder.toString(), pinelineData.getSerialNumber());
    };

    // B3XMV-A643D2 -> B3X-A643D2
    private static final Function<PipelineData, PipelineData> truncateModel = pinelineData -> {
        String model = pinelineData.getModel();
        int modelLength = Math.min(model.length(), MAX_NAME_TOKENS);
        String shortenedModel = model.substring(0,modelLength);
        return new PipelineData(shortenedModel, pinelineData.getSerialNumber());
    };

    // B3X-A643D2 -> B3X-D2
    // D-A643D2   -> D-3D2
    private static final Function<PipelineData, PipelineData> truncateSerialNumber = pinelineData -> {
        int serialNumberTruncatedLength = pinelineData.getModel().length() == 1 ? 3 : 2;
        String serialNumber = pinelineData.getSerialNumber();
        String truncatedSerialNumber = serialNumber.substring(serialNumber.length() - serialNumberTruncatedLength);
        return new PipelineData(pinelineData.getModel(), truncatedSerialNumber);
    };

    private static final BiFunction<String, String, String>
            droidNameAbbreviatorPipeline = instantiatePipelineData
                                  .andThen(convertModelToInitials)
                                  .andThen(convertRepeatInitialsToNumber)
                                  .andThen(truncateModel)
                                  .andThen(truncateSerialNumber)
                                  .andThen(Object::toString);

    private static class PipelineData {

        private final String model;
        private final String serialNumber;

        PipelineData(String model, String serialNumber) {
            this.model = model;
            this.serialNumber = serialNumber;
        }

        public String toString() {
            return this.model + NICKNAME_SEPARATOR + this.serialNumber;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public String getModel() {
            return model;
        }
    }
}
