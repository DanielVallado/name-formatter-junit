package com.uady;

import java.util.ArrayList;
import java.util.List;

public class NameProcessor {

    public static List<String> processNames(List<String> nameList) {
        List<String> formattedNameList = new ArrayList<>();
        for (String name : nameList) {
            String formattedName = NameFormatter.formatName(name);
            if (formattedName != null) {
                formattedNameList.add(formattedName);
            }
        }

        formattedNameList.sort(String::compareToIgnoreCase);
        return formattedNameList;
    }

}
