package br.com.nextel.integrator.main;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.launch.support.ExitCodeMapper;

import br.com.nextel.integrator.job.util.Constants;

public class IntegratorJobExitCodeMapper implements ExitCodeMapper {

	private static final Map<String, Integer> EXIT_CODES = new HashMap<>();
	static{
		EXIT_CODES.put(Constants.WARNING_EXIT_STATUS.getExitCode(), Constants.CD_RESULT_WARNING);
		EXIT_CODES.put(ExitStatus.COMPLETED.getExitCode(), Constants.CD_RESULT_COMPLETED);
		EXIT_CODES.put(ExitStatus.FAILED.getExitCode(), Constants.CD_RESULT_ABORT_ERROR);
	}
	
	
	@Override
	public int intValue(String exitCode) {
		Integer errorCode = (Integer)EXIT_CODES.get(exitCode);
		if(errorCode == null) errorCode = Constants.CD_RESULT_ABORT_ERROR;
		return errorCode;
	}

}
