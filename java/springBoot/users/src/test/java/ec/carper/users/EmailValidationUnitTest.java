package ec.carper.users;

import static org.junit.Assert.assertTrue;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;

import ec.carper.users.util.EmailValidation;

// https://www.baeldung.com/java-email-validation-regex
public class EmailValidationUnitTest {

    private String emailAddress;
    private String regexPattern;

    @Test
    public void testUsingEmailValidator() {
        emailAddress = "username@domain.com";
        assertTrue(EmailValidator.getInstance().isValid(emailAddress));
    }

    @Test
    public void testUsingSimpleRegex() {
        emailAddress = "username@domain.com";
        regexPattern = "^(.+)@(\\S+)$";
        assertTrue(EmailValidation.patternMatches(emailAddress, regexPattern));
    }    

    @Test
    public void testUsingStrictRegex() {
        emailAddress = "username@domain.com";
        regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        assertTrue(EmailValidation.patternMatches(emailAddress, regexPattern));
    }

    @Test
    public void testUsingRFC5322Regex() {
        emailAddress = "username@domain.com";
        regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        assertTrue(EmailValidation.patternMatches(emailAddress, regexPattern));
    }
}
