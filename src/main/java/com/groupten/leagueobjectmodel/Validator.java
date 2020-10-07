package com.groupten.leagueobjectmodel;

public class Validator {
    static public boolean isStringValid(String s) {
        if (s.isBlank() || s.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
