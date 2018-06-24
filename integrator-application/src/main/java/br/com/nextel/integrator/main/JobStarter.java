package br.com.nextel.integrator.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.nextel.integrator.job.util.Constants;

public class JobStarter {

public static Logger log = Logger.getLogger(JobStarter.class);
	
	private ApplicationContext context;
	private JobLauncher jobLauncher;
	
	public JobStarter(){
		String[] springConfig  =
			{
				"config/config.xml"
			};

		context = new ClassPathXmlApplicationContext(springConfig);
		jobLauncher = (JobLauncher) context.getBean("jobLauncher");
	}
	
	protected int startJob(String jobName, String dtStartFilter, String dtEndFilter) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		String timestampExec = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		JobParametersBuilder jobParamBuilder = new JobParametersBuilder();

		jobParamBuilder.addString("timestampExec", timestampExec);
		
		if(dtStartFilter != null && !dtStartFilter.equals("")){
			jobParamBuilder.addString("dtStartFilter", dtStartFilter);
		}
		if(dtEndFilter != null && !dtEndFilter.equals("")){
			jobParamBuilder.addString("dtEndFilter", dtEndFilter);
		}
		
		Job job = (Job) context.getBean(jobName);
		JobExecution execution = jobLauncher.run(job, jobParamBuilder.toJobParameters());
		log.info(execution);
		log.info(execution.getStatus());
		
		if(execution.getExitStatus().equals(Constants.WARNING_EXIT_STATUS)){
			log.warn(execution.getExecutionContext().get(Constants.WARNING_CONTEXT_KEY));
		}
		
		return new IntegratorJobExitCodeMapper().intValue(execution.getExitStatus().getExitCode());
	}

}
