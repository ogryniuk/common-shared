//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.common.authentication;

import be.yildiz.common.collections.Lists;
import lombok.NonNull;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Check a login and a password following the requirement in the parameter
 * object.
 * Immutable class.
 * @author Grégory Van den Borre
 */
public final class AuthenticationChecker {

    /**
     * Parameter rules.
     */
    private final AuthenticationRules parameters;

    /**
     * Create a new AuthenticationChecker from rules.
     * @param parameters List of rules to apply when authenticating, cannot be null.
     */
    public AuthenticationChecker(@NonNull AuthenticationRules parameters) {
        this.parameters = parameters;
    }

    /**
     * Check if provided login are valid against the given rules.
     *
     * @param login    Login to check.
     * @param password The password is assumed to be already hashed so it will not be
     *                 checked.
     * @return The credentials created from the login and the password.
     * @throws CredentialException If the check fails.
     */
    public Credentials check(final String login, final Password password) throws CredentialException {
        List<AuthenticationError> errors = Lists.newList();
        this.checkLogin(login, errors);
        if (errors.isEmpty()) {
            return new Credentials(login, password);
        }
        throw new CredentialException(errors);
    }

    /**
     * Check if provided login and password are valid against the given rules.
     *
     * @param login    Login to check.
     * @param password Password to check, provided in clear.
     * @return The credentials created from the login and the password, password
     * returned is hashed.
     * @throws CredentialException If the check fails.
     * @throws NullPointerException If login or password is null.
     */
    public Credentials check(final String login, final String password) throws CredentialException {
        List<AuthenticationError> errors = Lists.newList();
        this.checkLogin(login, errors);
        this.checkPassword(password, errors);
        if (errors.isEmpty()) {
            return new Credentials(login, new PasswordToHash(password));
        }
        throw new CredentialException(errors);
    }

    /**
     * Check the login against the given rules.
     *
     * @param login  Login to check.
     * @param errors List to store the errors.
     * @throws NullPointerException If login or errors is null.
     */
    private void checkLogin(final String login, final List<AuthenticationError> errors) {
        if (login.length() < this.parameters.loginMinLength) {
            errors.add(AuthenticationError.LOGIN_TOO_SHORT);
        } else if (login.length() > this.parameters.passMaxLength) {
            errors.add(AuthenticationError.LOGIN_TOO_LONG);
        }
        if (!Pattern.matches(this.parameters.loginPattern.pattern(), login)) {
            errors.add(AuthenticationError.INVALID_LOGIN_CHAR);
        }
    }

    /**
     * Check the password against the given rules.
     *
     * @param password Password to check.
     * @param errors   List to store the errors.
     * @throws NullPointerException If password or errors is null.
     */
    private void checkPassword(final String password, final List<AuthenticationError> errors) {
        if (password.length() < this.parameters.passMinLength) {
            errors.add(AuthenticationError.PASS_TOO_SHORT);
        } else if (password.length() > this.parameters.passMaxLength) {
            errors.add(AuthenticationError.PASS_TOO_LONG);
        }
        if (!Pattern.matches(this.parameters.passPattern.pattern(), password)) {
            errors.add(AuthenticationError.INVALID_PASS_CHAR);
        }
    }

    /**
     * Type or errors for credential values.
     *
     * @author Grégory Van den Borre
     */
    public enum AuthenticationError {

        /**
         * The login contains invalids characters.
         */
        INVALID_LOGIN_CHAR("connect.login.invalid"),

        /**
         * The password contains invalid characters.
         */
        INVALID_PASS_CHAR("connect.pwd.invalid"),

        /**
         * The login contains too many characters.
         */
        LOGIN_TOO_LONG("connect.login_long"),

        /**
         * The login does not contain enough characters.
         */
        LOGIN_TOO_SHORT("connect.login_short"),

        /**
         * The password contains too many characters.
         */
        PASS_TOO_LONG("connect.pwd_long"),

        /**
         * The password does not contain enough characters.
         */
        PASS_TOO_SHORT("connect.pwd_short");

        /**
         * Key associated to the error message.
         */
        public final String messageKey;

        /**
         * Initialize the enum value.
         *
         * @param messageKey Translation key associated to the error message.
         * @Requires messageKey != null.
         */
        AuthenticationError(final String messageKey) {
            this.messageKey = messageKey;
        }

    }

}
