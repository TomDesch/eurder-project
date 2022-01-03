package org.descheemaeker.tom.eurderproject.services;

import org.descheemaeker.tom.eurderproject.domain.Features;
import org.descheemaeker.tom.eurderproject.domain.User;
import org.descheemaeker.tom.eurderproject.exception.EmailDoesNotExistException;
import org.descheemaeker.tom.eurderproject.exception.LoginCredentialsMismatchException;
import org.descheemaeker.tom.eurderproject.exception.NoAccessToFeatureException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Base64;

@Service
@Transactional
public class SecurityService {
    private final UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public void validate(String authorization, Features feature) {
        User user = validateLoginCredentials(authorization);
        validateAccessToFeature(user, feature);
    }

    private User validateLoginCredentials(String authorization) {
        String[] loginCredentials = getLoginCredentials(authorization);
        String email = loginCredentials[0];
        String password = loginCredentials[1];

        User user = validateEmail(email);
        validatePassword(user, password);

        return user;
    }

    private User validateEmail(String email) {
        User user = this.userService.getByEmail(email);
        if (user == null) {
            throw new EmailDoesNotExistException();
        }
        return user;
    }

    private void validatePassword(User user, String password) {
        if (!user.getPassword().equals(password)) {
            throw new LoginCredentialsMismatchException();
        }
    }

    private String[] getLoginCredentials(String authorization) {
        String decodedUsernamePassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        return new String[]{getEmailAsString(decodedUsernamePassword), getPasswordAsString(decodedUsernamePassword)};
    }

    private String getEmailAsString(String decoded) {
        return decoded.substring(0, decoded.indexOf(":"));
    }

    private String getPasswordAsString(String decoded) {
        return decoded.substring(decoded.indexOf(":") + 1);
    }

    private void validateAccessToFeature(User user, Features feature) {
        if (!user.isAbleTo(feature)) {
            throw new NoAccessToFeatureException(user.getEmailAddress());
        }
    }
}
