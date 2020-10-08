package com.groupten.leagueobjectmodeltests;

import com.groupten.leagueobjectmodel.validators.Validator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {
    @Test
    public void areStringsValidTest() {
        assertTrue(Validator.areStringsValid("Hello"));
        assertTrue(Validator.areStringsValid("Hello", "World"));
        assertFalse(Validator.areStringsValid(" "));
        assertFalse(Validator.areStringsValid("Hello", " "));
    }

    @Test
    public void isPositionValidTest() {
        assertTrue(Validator.isPositionValid("forward"));
        assertTrue(Validator.isPositionValid("goalie"));
        assertTrue(Validator.isPositionValid("defense"));
        assertTrue(Validator.isPositionValid("Defense"));
        assertFalse(Validator.isPositionValid("hello"));
    }

}
