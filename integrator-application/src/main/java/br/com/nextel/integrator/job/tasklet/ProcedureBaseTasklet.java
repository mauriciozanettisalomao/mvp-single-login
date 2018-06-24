package br.com.nextel.integrator.job.tasklet;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.InitializingBean;

import br.com.nextel.integrator.job.util.Constants;


public abstract class ProcedureBaseTasklet implements Tasklet, InitializingBean {


	protected final Logger logger = LoggerFactory.getLogger(getClass());
	

	protected void checkResult(ChunkContext chunkContext, Map<String, Object> procResult) throws Exception {
		boolean errorCodeAbort = false;

		Integer cdResult = (Integer)procResult.get("P_CD_RESULT");
		String errorMessage = "message: "+ procResult.get("P_DE_RESULT");

		switch(cdResult){
			case Constants.CD_RESULT_ABORT_ERROR:
				errorCodeAbort = true;
				break;
			case Constants.CD_RESULT_WARNING:
				chunkContext
                .getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .put(Constants.WARNING_CONTEXT_KEY, errorMessage);
				break;
			default:
		}
		
		logger.info("## Printing Proc Result begin...");
		logger.info(String.format("## key = %s, value = %s", "P_CD_RESULT", cdResult));
		logger.info(String.format("## key = %s, value = %s", "P_DE_RESULT", errorMessage));
		logger.info("## Printing Proc Result finish.");
		
		if (errorCodeAbort){
			logger.info("## Error Abort");
			throw new Exception("Error "+errorMessage);
		}
	}

}
