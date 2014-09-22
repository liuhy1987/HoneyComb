/**
 * 
 */
package com.metarnet.hc.jobs;

import com.metarnet.hc.jobs.impl.AlarmPreProcessJob;
import com.metarnet.hc.jobs.impl.IncrementAnalysisJob;
import com.metarnet.hc.jobs.impl.SenderJob;
import com.metarnet.hc.jobs.impl.StoreAlarmsJob;

/**
 * @author liuhy
 */
public class JobFactory {
	
	private static JobFactory jf = null;
	public static synchronized JobFactory getInstance(){
		if(jf==null) jf = new JobFactory();
		return jf;
	}

	private JobFactory(){
		SenderJob sj = new SenderJob(null);
		StoreAlarmsJob store = new StoreAlarmsJob(sj);
		
		IncrementAnalysisJob increment = new IncrementAnalysisJob(store);
		syncalmproc = new AlarmPreProcessJob(increment);
		
		repoalmproc = new AlarmPreProcessJob(store);
	}
	
	private IJob repoalmproc;
	private IJob syncalmproc;
	public IJob getReportAlmProc(){
		return repoalmproc;
	}
	
	public IJob getSyncAlmProc(){
		return syncalmproc;
	}
	
}
