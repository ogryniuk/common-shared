package be.yildiz.common.authentication;

import be.yildiz.common.collections.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class CredentialExceptionTest {

    public static class Constructor {

        @Test
        public void happyFlow() {
            new CredentialException(Lists.newList());
        }

        @Test(expected = NullPointerException.class)
        public void withNullParameter() {
            new CredentialException(null);
        }
    }

    public static class GetErrors {

        @Test
        public void happyFlow() {
            CredentialException ce = new CredentialException(Lists.newList(
                    AuthenticationChecker.AuthenticationError.INVALID_LOGIN_CHAR,
                    AuthenticationChecker.AuthenticationError.INVALID_PASS_CHAR));
            Assert.assertEquals(2, ce.getErrors().size());
            Assert.assertTrue(ce.getErrors().contains(AuthenticationChecker.AuthenticationError.INVALID_LOGIN_CHAR));
            Assert.assertTrue(ce.getErrors().contains(AuthenticationChecker.AuthenticationError.INVALID_PASS_CHAR));
        }

        @Test(expected = UnsupportedOperationException.class)
        public void ensureImmutable() {
            CredentialException ce = new CredentialException(Lists.newList(
                    AuthenticationChecker.AuthenticationError.INVALID_LOGIN_CHAR,
                    AuthenticationChecker.AuthenticationError.INVALID_PASS_CHAR));
            ce.getErrors().remove(AuthenticationChecker.AuthenticationError.INVALID_LOGIN_CHAR);
        }

        @Test
        public void ensureCopy() {
            List<AuthenticationChecker.AuthenticationError> l = Lists.newList(
                    AuthenticationChecker.AuthenticationError.INVALID_LOGIN_CHAR,
                    AuthenticationChecker.AuthenticationError.INVALID_PASS_CHAR);
            CredentialException ce = new CredentialException(l);
            Assert.assertEquals(2, l.size());
            Assert.assertEquals(2, ce.getErrors().size());

            l.add(AuthenticationChecker.AuthenticationError.LOGIN_TOO_LONG);
            Assert.assertEquals(3, l.size());
            Assert.assertEquals(2, ce.getErrors().size());
        }
    }

}