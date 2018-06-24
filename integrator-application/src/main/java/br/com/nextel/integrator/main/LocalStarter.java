package br.com.nextel.integrator.main;

import br.com.nextel.integrator.job.util.Constants;

public class LocalStarter extends IntegratorMain{
	
	public static void main(String[] args) {

		try	{
			
			Object[][] jobs = {
					{"loadImage",null,null},
					};
			for(Object[] job: jobs){
				try {
					
					int errorCode = 0;
					
					errorCode = new IntegratorMain().startJob((String)job[0],(String)job[1],(String)job[2]);
				
					System.exit(errorCode);
		
				}catch (Exception e) {
					log.error(e);
					System.exit(Constants.CD_RESULT_ABORT_ERROR);
				}
			}

		}catch(Exception e)	{
			e.printStackTrace();
		}

	}

}
