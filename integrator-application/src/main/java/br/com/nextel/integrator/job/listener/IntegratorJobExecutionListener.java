package br.com.nextel.integrator.job.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import br.com.nextel.integrator.job.util.Constants;

public class IntegratorJobExecutionListener implements JobExecutionListener {

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)){
			if(jobExecution.getExecutionContext().containsKey(Constants.WARNING_CONTEXT_KEY)){
				jobExecution.setExitStatus(Constants.WARNING_EXIT_STATUS);
			}
		}
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		
	}

}
