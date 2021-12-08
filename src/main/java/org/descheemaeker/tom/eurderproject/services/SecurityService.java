package org.descheemaeker.tom.eurderproject.services;

import org.descheemaeker.tom.eurderproject.domain.Features;
import org.descheemaeker.tom.eurderproject.domain.User;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {
    private final UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public User validate(String authorization, Features feature) {
        User user = validateUserName(authorization);
        validateAccessToFeature(user, feature);
        return user;
    }

    private User validateUserName(String authorization) {
        String email = getEmailAsString(authorization);

        User user = this.userService.getByEmail(email);

        //todo fix nullable
//        if (user == null)
//            throw new UnknownUserException("No user corresponds to : " + email);

        return user;
    }

    private String getEmailAsString(String authorization) {
        String decodeUsernamePassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        return decodeUsernamePassword.substring(0, decodeUsernamePassword.indexOf(":"));
    }

    private void validateAccessToFeature(User user, Features feature) {
        if (!user.isAbleTo(feature)){
            //todo error?
        }
            //throw new UnauthorizedException(user.getEmail() + " does not have access to " + feature.name());
    }
}
