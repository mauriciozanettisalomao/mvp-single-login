package br.com.nextel.integrator.db.dao;

import java.sql.Types;
import java.util.Map;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.StoredProcedure;

public class SimpleProcedureCallDao extends StoredProcedure {

	public SimpleProcedureCallDao(DriverManagerDataSource dataSource, String procedureName) {
		super(dataSource, procedureName);
		setFunction(false);
	}
	
	public Map<String,Object> executeProc(long contextId, String contextFilterStartDate, String contextFilterEndDate) {
		
		SqlParameter contextIdParam = new SqlParameter("P_ID_JOB_EXECUTION", Types.INTEGER);
		SqlParameter contextFilterStartDateParam = new SqlParameter("P_DT_START_FILTER_PARAM", Types.VARCHAR);
		SqlParameter contextFilterEndDateParam = new SqlParameter("P_DT_END_FILTER_PARAM", Types.VARCHAR);
		SqlOutParameter cdResult = new SqlOutParameter("P_CD_RESULT", Types.INTEGER);
		SqlOutParameter msgResult = new SqlOutParameter("P_DE_RESULT", Types.VARCHAR);
		SqlParameter[] paramArray = {contextIdParam, contextFilterStartDateParam, contextFilterEndDateParam, cdResult, msgResult};		
		setParameters(paramArray);
		compile();
		return execute(contextId, contextFilterStartDate, contextFilterEndDate);
		
	}
	
}
