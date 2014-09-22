/**
 * 
 */
package com.metarnet.hc.proc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

import com.metarnet.hc.core.ResourceFactory;
import com.metarnet.hc.jobs.IJob;
import com.metarnet.hc.jobs.JobFactory;
import com.metarnet.hc.jobs.JobMonitor;
import com.metarnet.hc.model.AlarmModel;
import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.util.Constant;

/**
 * @author liuhy
 */
public class SyncAlmWorker implements IAlmWorker {
	
	private String domain;
	private WorkTHread[] wh = null;
	private IJob job = null;
	private ArrayBlockingQueue<AlmMsg> alms = new ArrayBlockingQueue<AlmMsg>(Constant.SYNCSIZE);
	//TODO tmp map should be checked periodically in case of losing pack 
	private Map<String, SyncAlarmMsg> tmp = new HashMap<String, SyncAlarmMsg>(); 
	
	public SyncAlmWorker(String domain) {
		this.domain = domain;
		job = JobFactory.getInstance().getSyncAlmProc();
		wh = new WorkTHread[Constant.SYNCWORKTHREADNUM];
		for(int i=0; i<Constant.SYNCWORKTHREADNUM; i++){
			wh[i] = new WorkTHread(domain+"-SYNC-"+i, this);
			wh[i].start();
		}
	}
	
	public void add(AlmMsg msg) {
		synchronized (tmp) {
			SyncAlarmMsg tmpmsg = tmp.get(msg.getId());
			if(tmpmsg==null){
				tmpmsg = new SyncAlarmMsg();
				tmp.put(msg.getId(), tmpmsg);
				
				//query current alarms
				List<AlarmModel> curalms = ResourceFactory.getDb().queryCurrAlarms(domain);
				JobMonitor.getInstance().getJobStatus(msg.getId()).getContext().put(Constant.CURRENTALARMS, curalms);
			}
			if(tmpmsg.add(msg)){
				tmp.remove(msg.getId());
				alms.add(tmpmsg.getAllAlmMsg());
			}
		}
	}
	
	public AlmMsg getData() throws InterruptedException {
		return alms.take();
	}

	public IJob getJob() {
		return job;
	}

	public void shutdown() {
		for(int i=0; i<Constant.SYNCWORKTHREADNUM; i++){
			wh[i].shutdown();
		}
		alms.clear();
		tmp.clear();
	}
	
}
