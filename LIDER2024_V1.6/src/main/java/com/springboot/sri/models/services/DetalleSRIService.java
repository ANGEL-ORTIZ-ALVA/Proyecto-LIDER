package com.springboot.sri.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sri.models.entitys.DetalleSRIId;
import com.springboot.sri.models.entitys.DetalleSri;
import com.springboot.sri.models.repository.DetalleSRIRepository;

@Service
public class DetalleSRIService {
	@Autowired
	 private DetalleSRIRepository detalleSRIRepository;
	 
	 public List<DetalleSri> listarTodas() {
	        return detalleSRIRepository.findAll();
	    }

	    public Optional<DetalleSri> buscarPorId(DetalleSRIId id) {
	        return detalleSRIRepository.findById(id);
	    }

	    public DetalleSri guardar(DetalleSri detalleSRI) {
	        return detalleSRIRepository.save(detalleSRI);
	    }

	    public void eliminarPorId(DetalleSRIId id) {
	        detalleSRIRepository.deleteById(id);
	    }
	    
	 // Método para listar detalles por id_SRI
	    public List<DetalleSri> listarPorSriId(int sriId) {
	        return detalleSRIRepository.findBySriId(sriId);
	    }
}
