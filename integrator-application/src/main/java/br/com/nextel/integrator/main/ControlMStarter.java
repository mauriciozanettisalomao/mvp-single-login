package br.com.nextel.integrator.main;

import org.apache.log4j.Logger;

import br.com.nextel.integrator.job.util.Constants;

public class ControlMStarter extends IntegratorMain{
	public static Logger log = Logger.getLogger(ControlMStarter.class);

	public static void main(String[] args) {

		if (args == null || args.length == 0){
			log.error("Nenhum parametro para iniciar o processo");
			System.exit(Constants.CD_RESULT_ABORT_ERROR);
		}
		
		String jobName = null;
		String startDate = null;
		String dtEndFilter = null;
		
		for(String arg: args){
			String[] tmp = arg.split("=");
			if(tmp.length < 2){
				log.warn("Parametro invalido: " + arg);
				continue;
			}
			switch(tmp[0]){
			case "jobName":
				jobName = tmp[1];
				break;
			case "dtStartFilter":
				startDate = tmp[1];
				break;
			case "dtEndFilter":
				dtEndFilter = tmp[1];
				break;				
			default:
				log.warn("Parametro ignorado: " + arg);
			}
		}
		
		if (jobName == null){
			log.error("Parametro jobName nao informado.");
			System.exit(Constants.CD_RESULT_ABORT_ERROR);
		}
		
		try {
			
			log.info("Iniciando job "+jobName);
			
			workflowIntegratorMainJob(jobName,startDate,dtEndFilter);
						
		} catch (Exception e) {
			log.error(e);
			System.exit(Constants.CD_RESULT_ABORT_ERROR);
		}

	}

}
