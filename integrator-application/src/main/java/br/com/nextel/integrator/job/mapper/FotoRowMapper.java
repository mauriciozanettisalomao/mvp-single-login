package br.com.nextel.integrator.job.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.nextel.integrator.db.model.Foto;

public class FotoRowMapper implements RowMapper<Foto> {

	@Override
	public Foto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Foto foto = new Foto();
		
		foto.setNumemp(rs.getString("numemp"));
		foto.setFoto(rs.getBytes("fotemp"));
		//foto.setFoto(rs.get("fotemp"));
		
		return foto;
	}

}
