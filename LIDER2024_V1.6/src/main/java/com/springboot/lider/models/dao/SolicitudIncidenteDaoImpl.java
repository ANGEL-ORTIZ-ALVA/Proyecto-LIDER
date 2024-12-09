package com.springboot.lider.models.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.lider.models.entitys.SolicitudIncidente;
@Repository
public class SolicitudIncidenteDaoImpl implements ISolicitudIncidenteDao {
	@Autowired
    private JdbcTemplate jdbc;

    @Override
    public Page<SolicitudIncidente> listar(Pageable pageable) {
        String sql = "SELECT * FROM Solicitud_incidente";
        List<SolicitudIncidente>listaSolicitudes = jdbc.query(sql,BeanPropertyRowMapper.newInstance(SolicitudIncidente.class));
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), listaSolicitudes.size());
        Page<SolicitudIncidente> solinPage = new PageImpl<>(listaSolicitudes.subList(start, end), pageable, listaSolicitudes.size());
        return solinPage;
    }
    
    @Override
    public int guardar(SolicitudIncidente solicitud) {
        String sql = "INSERT INTO Solicitud_incidente (id_cliente, fecha_solicitud, incidente, estado, fecha_aprobacion, fecha_rechazo, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbc.update(sql, solicitud.getIdCliente(), solicitud.getFechaSolicitud(), solicitud.getIncidente(), solicitud.getEstado(), solicitud.getFechaAprobacion(), solicitud.getFechaRechazo(), solicitud.getObservaciones());
    }


    @Override
    public SolicitudIncidente buscarPorId(int id) {
        String sql = "SELECT * FROM Solicitud_incidente WHERE id = ?";
        return jdbc.queryForObject(sql, new Object[] { id }, 
        		BeanPropertyRowMapper.newInstance(SolicitudIncidente.class));
    }

    @Override
    public int actualizar(SolicitudIncidente solicitud) {
        String sql = "UPDATE Solicitud_incidente SET id_cliente=?, fecha_solicitud=?, incidente=?, estado=?, fecha_aprobacion=?, fecha_rechazo=?, observaciones=? WHERE id=?";
        return jdbc.update(sql, solicitud.getIdCliente(), solicitud.getFechaSolicitud(), solicitud.getIncidente(), solicitud.getEstado(), solicitud.getFechaAprobacion(), solicitud.getFechaRechazo(), solicitud.getObservaciones(), solicitud.getId());
    }

    @Override
    public int eliminar(int id) {
        String sql = "DELETE FROM Solicitud_incidente WHERE id=?";
        return jdbc.update(sql, id);
    }

	@Override
	public int contarSI() {
		String sql = "SELECT COUNT(*) FROM Solicitud_incidente";
        return jdbc.queryForObject(sql, Integer.class);
	}
	
	/*filtrado por fechas*/
	@Override
    public List<SolicitudIncidente> filtrarPorFecha(Date fechaInicio, Date fechaFin) {
        String sql = "SELECT * FROM Solicitud_incidente WHERE fecha_solicitud BETWEEN ? AND ?";
        return jdbc.query(sql, new Object[]{fechaInicio, fechaFin}, BeanPropertyRowMapper.newInstance(SolicitudIncidente.class));
    }

}
