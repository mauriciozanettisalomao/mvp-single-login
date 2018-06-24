package br.com.nextel.integrator.job.util;

import org.springframework.batch.core.ExitStatus;

public class Constants {

	public static final int CD_RESULT_COMPLETED = 0;
	public static final int CD_RESULT_WARNING = 1;
	public static final int CD_RESULT_ABORT_ERROR = 2;
	public static final String WARNING_CONTEXT_KEY = "WARNING_CONTEXT_KEY";
	public static final ExitStatus WARNING_EXIT_STATUS = new ExitStatus("COMPLETED_WITH_WARNING","Execução completada com WARNING.");
	
		
}
