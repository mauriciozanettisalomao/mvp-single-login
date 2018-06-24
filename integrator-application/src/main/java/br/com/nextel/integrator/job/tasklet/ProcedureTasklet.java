package br.com.nextel.integrator.job.tasklet;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.Assert;

import br.com.nextel.integrator.db.dao.SimpleProcedureCallDao;


public class ProcedureTasklet extends ProcedureBaseTasklet {

	private DriverManagerDataSource dataSource;
	
	private String procedureName;
	
	private long jobExecutionId;
	
	private String startDateFilterParam;
	
	private String endDateFilterParam;
	
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

	public String getStartDateFilterParam() {
		return startDateFilterParam;
	}

	public void setStartDateFilterParam(String startDateFilterParam) {
		this.startDateFilterParam = startDateFilterParam;
	}
	
	public String getEndDateFilterParam() {
		return endDateFilterParam;
	}

	public void setEndDateFilterParam(String endDateFilterParam) {
		this.endDateFilterParam = endDateFilterParam;
	}	
	
	public long getJobExecutionId() {
		return jobExecutionId;
	}

	public void setJobExecutionId(long jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(dataSource, "dataSource must be set");
		Assert.notNull(procedureName, "procedureName must be set");
		Assert.notNull(jobExecutionId, "directory must be set");
	}	
	
	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

		SimpleProcedureCallDao procedureCallDao = new SimpleProcedureCallDao(dataSource, procedureName);
		Map<String, Object> procResult = procedureCallDao.executeProc(jobExecutionId, startDateFilterParam, endDateFilterParam);		

		checkResult(chunkContext, procResult);
		
		return RepeatStatus.FINISHED;	
	}

}
