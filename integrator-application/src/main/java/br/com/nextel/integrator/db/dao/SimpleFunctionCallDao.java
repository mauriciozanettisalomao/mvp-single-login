package br.com.nextel.integrator.db.dao;

import java.sql.Types;
import java.util.Map;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.StoredProcedure;

public class SimpleFunctionCallDao extends StoredProcedure {

	public SimpleFunctionCallDao(DriverManagerDataSource dataSource, String procedureName) {
		super(dataSource, procedureName);
		setFunction(true);
	}

	public String executeFunc(String cdParam) {
		
		String returnFunction = null;
		Object outFunction = null;
		
		SqlParameter parameter;

		parameter = new SqlParameter("P_CD_PARAMETER", Types.VARCHAR);
		SqlParameter[] paramArray = {new SqlOutParameter(returnFunction,Types.VARCHAR), parameter};
		setParameters(paramArray);
		compile();
		
		Map<?, ?> out = execute(cdParam);
		
		if(!out.isEmpty()){
        	outFunction = out.get(returnFunction);
		}
		
		return (outFunction!=null) ? outFunction.toString() : "";
		
    }
	
}
