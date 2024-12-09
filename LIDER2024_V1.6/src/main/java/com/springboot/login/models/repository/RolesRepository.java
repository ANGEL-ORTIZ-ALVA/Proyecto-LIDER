package com.springboot.login.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.login.models.entitys.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>{

}
