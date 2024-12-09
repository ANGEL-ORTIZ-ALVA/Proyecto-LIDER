package com.springboot.login.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.login.models.entitys.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
