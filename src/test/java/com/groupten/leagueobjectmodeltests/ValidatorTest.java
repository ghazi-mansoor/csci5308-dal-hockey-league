package com.groupten.leagueobjectmodeltests;

import com.groupten.leagueobjectmodel.Validator;
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
}
