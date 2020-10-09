package com.groupten.validator;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    static public boolean areStringsValid(String ... args) {
        List<Boolean> checks = new ArrayList<Boolean>();

        for (String s : args) {
            if (s.isEmpty() || s.isBlank() || s.equals("null") || s.equals("NULL") || s.equals("Null")) {
                checks.add(false);
            } else {
                checks.add(true);
            }
        }

        return !checks.contains(false);
    }

    static public boolean isPositionValid(String position) {
        String[] validPositions = {"goalie", "forward", "defense"};
        position = position.toLowerCase();

        for (String s : validPositions) {
            if (s.equals(position)) {
                return true;
            }
        }
        return false;
    }
}
