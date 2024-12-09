package com.springboot.sri.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

//import com.springboot.sorea.models.entitys.Sorea;
import com.springboot.sri.models.entitys.Sri;
import com.springboot.sri.models.repository.SriRepository;

@Service
public class SriService {
	@Autowired
    private SriRepository sriRepository;
	
	public List<Sri> listarTodas() {
        return sriRepository.findAll();
    }
	
	public Optional<Sri> buscarPorId(int id) {
        return sriRepository.findById(id);
    }

    public Sri guardar(Sri sri) {
        return sriRepository.save(sri);
    }

    public void eliminarPorId(int id) {
        sriRepository.deleteById(id);
    }
    
    public List<Sri> findByEstado(Sri.Estado estado) {
        return sriRepository.findByEstado(estado);
    }
    
    /*public Page<Sri> listarSri(Pageable pageable) {
		List<Sri> listaReabastecimiento = sriRepository.findAll();
		int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), listaReabastecimiento.size());
	    Page<Sri> reabastecimientoPage = new PageImpl<>(listaReabastecimiento.subList(start, end), pageable, listaReabastecimiento.size());
		return reabastecimientoPage;
	}*/
    public Page<Sri> listarSri(Pageable pageable) {
        return sriRepository.findAll(pageable);
    }
}
