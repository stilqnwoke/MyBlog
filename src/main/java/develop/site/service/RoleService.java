package develop.site.service;

import develop.site.model.entity.RoleEntity;
import develop.site.model.service.RoleServiceModel;

import java.util.Set;

public interface RoleService {

    RoleServiceModel findByName(String name);

    Set<RoleServiceModel> findAllRoles();
}
