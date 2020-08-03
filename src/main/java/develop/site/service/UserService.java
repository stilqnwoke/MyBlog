package develop.site.service;


import develop.site.model.entity.UserEntity;
import develop.site.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    UserEntity getAuthenticatedUser();

}