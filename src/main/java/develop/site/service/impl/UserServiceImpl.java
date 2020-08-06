package develop.site.service.impl;

import develop.site.model.entity.RoleEntity;
import develop.site.model.entity.UserEntity;
import develop.site.model.service.UserServiceModel;
import develop.site.repository.UserRepository;
import develop.site.service.RoleService;
import develop.site.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }


    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {

        UserEntity user = this.modelMapper.map(userServiceModel, UserEntity.class);

        if (this.userRepository.count() == 0) {
            user.setAuthorities(this.roleService.findAllRoles()
                    .stream()
                    .map(r -> this.modelMapper.map(r, RoleEntity.class))
                    .collect(Collectors.toSet()));
        }else {
            user.setAuthorities(new LinkedHashSet());

            RoleEntity roleEntity=this.modelMapper
                    .map(this.roleService.findByName("ROLE_USER"),RoleEntity.class);
            user.getAuthorities().add(roleEntity);
        }



        user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
        return this.modelMapper
                .map(this.userRepository.saveAndFlush(user),
                        UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.userRepository
                .findByUsername(username)
                .map(user -> this.modelMapper
                        .map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserEntity getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loggedUser = (UserEntity) auth.getPrincipal();
        Long userId = Long.valueOf(loggedUser.getId());
        return userRepository.findById(userId);
    }

    @Override
    public UserServiceModel findUserByUserName(String username) {

        return this.userRepository.findByUsername(username)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElseThrow(()-> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    public UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword) {
        UserEntity user = this.userRepository.findByUsername
                (userServiceModel.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found!"));

        if (!this.passwordEncoder.matches(oldPassword, user.getPassword())){
            throw new IllegalArgumentException("Incorrect password");
        }

        user.setPassword(!"".equals(userServiceModel.getPassword()) ?
                this.passwordEncoder.encode(userServiceModel.getPassword()) :
                user.getPassword());

        user.setEmail(userServiceModel.getEmail());

        return this.modelMapper.map(this.userRepository.saveAndFlush(user),UserServiceModel.class);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s).orElse(null);
    }
}

