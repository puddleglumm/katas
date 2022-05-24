package com.github.puddleglumm;

public class DroidNamer {

    private static final int MAX_NAME_TOKENS = 3;

    public static void main(String[] args) {
        String modelName = "Homework Processor";
        String serialNumber = "A643D2";

        System.out.println("Droid model " + modelName + " with serial number " + serialNumber +
                            " has nickname " + abbreviateName(modelName, serialNumber));
    }

    public static String abbreviateName(String modelName, String serialNumber) {
        StringBuilder abbreviatedName = new StringBuilder(5);

        abbreviatedName.append(abbreviateModelName(modelName));
        abbreviatedName.append("-");
        abbreviatedName.append(abbreviateSerialNumber(serialNumber, abbreviatedName.length() - 1));

        return abbreviatedName.toString();
    }

    private static String abbreviateSerialNumber(String serialNumber, int abbreviatedModelLength) {
        if (abbreviatedModelLength == 1) {
            return serialNumber.substring(serialNumber.length() - 3);
        } else {
            return serialNumber.substring(serialNumber.length() - 2);
        }
    }

    private static String abbreviateModelName(String modelName) {
        String modelNameTokens[] = modelName.split(" ");
        int numOfTokensToAbbreviate = Math.min(modelNameTokens.length, MAX_NAME_TOKENS);
        StringBuilder abbreviatedModelName = new StringBuilder(numOfTokensToAbbreviate);
        for(int i = 0; i < numOfTokensToAbbreviate; i++ ) {
            abbreviatedModelName.append(modelNameTokens[i].charAt(0));
        }

        return abbreviatedModelName.toString();
    }

}
