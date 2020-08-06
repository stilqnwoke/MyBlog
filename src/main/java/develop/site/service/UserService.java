package develop.site.service;


import develop.site.model.entity.UserEntity;
import develop.site.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    UserEntity getAuthenticatedUser();

    UserServiceModel findUserByUserName(String username);

    UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword);

    List<UserServiceModel> findAllUsers();

}