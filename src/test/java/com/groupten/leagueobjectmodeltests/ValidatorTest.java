package com.groupten.leagueobjectmodeltests;

import com.groupten.leagueobjectmodel.Validator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {
    @Test
    public void isStringValidTest() {
        assertTrue(Validator.isStringValid("Hello World!"));
        assertFalse(Validator.isStringValid(" "));
    }
}
