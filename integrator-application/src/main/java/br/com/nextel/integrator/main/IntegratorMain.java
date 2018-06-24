package br.com.nextel.integrator.main;

public class IntegratorMain extends JobStarter{

	public static void workflowIntegratorMainJob(String jobName, String dtStartFilter, String dtEndFilter) throws Exception{

		int errorCode = 0;
	
		errorCode = new IntegratorMain().startJob(jobName, dtStartFilter, dtEndFilter);
	
		System.exit(errorCode);
			
	}

}
