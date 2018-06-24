package br.com.nextel.integrator.job.util;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import br.com.nextel.integrator.db.dao.SimpleFunctionCallDao;

public class Util {

	private DriverManagerDataSource dataSource;
	private String procedureName;
	
	public DriverManagerDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getParameter(String cdParameter){
		
		SimpleFunctionCallDao functionCallDao = new SimpleFunctionCallDao(dataSource, procedureName);
		
		String funcResult = functionCallDao.executeFunc(cdParameter);

		return funcResult;
	}

}
