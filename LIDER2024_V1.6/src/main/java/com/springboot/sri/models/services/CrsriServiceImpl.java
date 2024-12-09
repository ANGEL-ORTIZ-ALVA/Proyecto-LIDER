package com.springboot.sri.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sri.models.entitys.Crsri;
import com.springboot.sri.models.entitys.DetalleSri;
import com.springboot.sri.models.entitys.Insumo;
import com.springboot.sri.models.entitys.Sri;
import com.springboot.sri.models.repository.CrsriRepository;
import com.springboot.sri.models.repository.DetalleSRIRepository;
import com.springboot.sri.models.repository.InsumosRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CrsriServiceImpl implements ICrsriService{
	
	@Autowired
    private CrsriRepository crsriRepository;

	@Override
	public List<Crsri> listarCrsri() {
		return crsriRepository.findAll();
	}

	@Override
	public void guardarCrsri(Crsri cr) {
		crsriRepository.insertarCRSRI(cr.getIdSRI().getId(), cr.getFecha_recepcion(), cr.getHoraRecepcion(), cr.getPersonal_recepcion().getId_personal(), cr.getEstado_recepcion(), cr.getDescripcion());
	}

	@Override
	public void editarCrsri(Crsri cr) {
		crsriRepository.editarCRSRI(cr.getId(), cr.getFecha_recepcion(), cr.getHoraRecepcion(), cr.getPersonal_recepcion().getId_personal(), cr.getEstado_recepcion(), cr.getDescripcion());
		
	}

	@Override
	public Optional<Crsri> buscarId(int id) {
		return crsriRepository.findById(id);
	}

	@Override
	public void borrarCrsri(int id) {
		crsriRepository.eliminarCRSRI(id);
		
	}

	@Override
	public Crsri encontrarPorId(int id) {
		return crsriRepository.findById(id).orElse(null);
	}

	@Override
    public List<Crsri> findBySri(Sri idSRI) {
        return crsriRepository.findByIdSRI(idSRI);
    }
	
	@Override
	public boolean existsBySri(Sri sri) {
        return crsriRepository.existsByIdSRI(sri);
    }
	
/*Transaccional y Actualizar stock*/
	
	@Override
	@Transactional
	public Crsri save(Crsri crsri) {
		Crsri savedCrsri = crsriRepository.save(crsri);
        actualizarStock(savedCrsri, true);
        return savedCrsri;
	}

	@Override
	@Transactional
	public Crsri update(Crsri crsri) {
		Crsri updatedCrsri = crsriRepository.save(crsri);
        actualizarStock(updatedCrsri, true);
        return updatedCrsri;
	}

	@Override
	@Transactional
	public void delete(int id) {
		Crsri crsri = crsriRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CRSRI no encontrado"));
        actualizarStock(crsri, false);
        crsriRepository.delete(crsri);
		
	}
	
	@Autowired
    private DetalleSRIRepository detalleSriRepository;

    @Autowired
    private InsumosRepository insumosRepository;
	
	private void actualizarStock(Crsri crsri, boolean isAddition) {
        List<DetalleSri> detalles = detalleSriRepository.findBySriId(crsri.getIdSRI().getId());
        for (DetalleSri detalle : detalles) {
            Insumo insumo = insumosRepository.findById(detalle.getInsumo().getId())
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
            int nuevaCantidad = isAddition 
                ? insumo.getCantidad_stock() - detalle.getCantidad() 
                : insumo.getCantidad_stock() + detalle.getCantidad();
            insumo.setCantidad_stock(nuevaCantidad);
            insumosRepository.save(insumo);
        }
	}

}
