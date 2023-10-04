package com.uady;

public class NameFormatter {

    public static String formatName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }

        name = name.trim();  // Delete blank spaces
        String[] nameParts = name.split("\\s+");
        StringBuilder formattedName = new StringBuilder();

        for (String part : nameParts) {
            if (!formattedName.isEmpty()) {
                formattedName.append(" ");
            }
            formattedName.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1).toLowerCase());
        }

        return formattedName.toString();
    }

}
