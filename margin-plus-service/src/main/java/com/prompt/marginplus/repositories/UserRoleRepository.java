package com.prompt.marginplus.repositories;

import com.prompt.marginplus.entities.UserRole;
import com.prompt.marginplus.entities.UserRoleKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRoleRepo")
public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleKey>{
}
