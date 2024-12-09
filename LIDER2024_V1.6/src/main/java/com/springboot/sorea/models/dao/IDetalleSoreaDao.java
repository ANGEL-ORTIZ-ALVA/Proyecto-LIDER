package com.springboot.sorea.models.dao;

import java.util.List;
import com.springboot.sorea.models.entitys.DetalleSorea;

public interface IDetalleSoreaDao {
	//METODOS CRUD
			public List<DetalleSorea>listarDetalleSorea();
			public void guardarDetalleSorea(DetalleSorea detalleSOREA);
			public DetalleSorea buscarDetalleSorea(int idSorea, int idInsumo);
			public int actualizarDetalleSorea (DetalleSorea detalleSOREA);
			public int borrarDetalleSorea(int idSorea, int idInsumo);
			public List<DetalleSorea> obtenerProductosPorSolicitud(int id_SOREA);
			
			/*reportes*/
			public DetalleSorea buscarDetalleSoreaPorIdSorea(int idSorea);
}
