package develop.site.service.impl;



import develop.site.model.entity.RoleEntity;
import develop.site.model.service.RoleServiceModel;
import develop.site.repository.RoleRepository;
import develop.site.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    public RoleServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init(){
        if (this.roleRepository.count() == 0){
            RoleEntity admin = new RoleEntity("ROLE_ADMIN");
            RoleEntity user = new RoleEntity("ROLE_USER");

            this.roleRepository.save(admin);
            this.roleRepository.save(user);
        }

    }

    @Override
    public RoleServiceModel findByName(String name) {
        return this.roleRepository
                .findByAuthority(name)
                .map(role -> this.modelMapper.map(role, RoleServiceModel.class))
                .orElse(null);
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {

       return this.roleRepository.findAll()
               .stream()
               .map(r->this.modelMapper.map(r,RoleServiceModel.class))
               .collect(Collectors.toSet());
    }
}

