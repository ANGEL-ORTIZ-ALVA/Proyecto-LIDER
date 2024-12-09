package com.springboot.sri.models.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.sri.models.entitys.DetalleSRIId;
import com.springboot.sri.models.entitys.DetalleSri;

@Repository
public interface DetalleSRIRepository extends JpaRepository<DetalleSri, DetalleSRIId>{
	List<DetalleSri> findBySriId(int sriId); // Usa 'sri' seguido del nombre del campo que quieres filtrar
}