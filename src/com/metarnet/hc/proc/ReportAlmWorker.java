/**
 * 
 */
package com.metarnet.hc.proc;

import java.util.concurrent.ArrayBlockingQueue;

import com.metarnet.hc.jobs.IJob;
import com.metarnet.hc.jobs.JobFactory;
import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.util.Constant;

/**
 * @author liuhy
 *
 */
public class ReportAlmWorker implements IAlmWorker{

	private WorkTHread[] wh = null;
	private IJob job = null;
	private ArrayBlockingQueue<AlmMsg> alm = new ArrayBlockingQueue<AlmMsg>(Constant.RTALMQUEUESIZE);
	
	public ReportAlmWorker(String domain){
		job = JobFactory.getInstance().getReportAlmProc();
		wh = new WorkTHread[Constant.RTWORKTHREADNUM];
		for(int i=0; i<Constant.RTWORKTHREADNUM; i++){
			wh[i] = new WorkTHread(domain+"-RT-"+i, this);
			wh[i].start();
		}
	}
	
	public void shutdown(){
		for(int i=0; i<Constant.RTWORKTHREADNUM; i++){
			wh[i].shutdown();
		}
		alm.clear();
	}
	
	public void add(AlmMsg msg) {
		alm.add(msg);
	}

	public AlmMsg getData() throws InterruptedException{
		return alm.take();
	}

	public IJob getJob() {
		return job;
	}
	
}
