package com.groupten.leagueobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    static public boolean areStringsValid(String ... args) {
        List<Boolean> checks = new ArrayList<Boolean>();

        for (String s : args) {
            if (s.isEmpty() || s.isBlank()) {
                checks.add(false);
            } else {
                checks.add(true);
            }
        }

        if (checks.contains(false)){
            return false;
        } else {
            return true;
        }
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
