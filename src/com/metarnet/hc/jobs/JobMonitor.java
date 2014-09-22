/**
 * 
 */
package com.metarnet.hc.jobs;

import java.util.HashMap;
import java.util.Map;

import com.metarnet.hc.model.JobStatus;

/**
 * @author liuhy
 */
public class JobMonitor {
	
	private Map<String, JobStatus> jobs = new HashMap<String, JobStatus>();
	
	private static JobMonitor jm = null;
	public static synchronized JobMonitor getInstance(){
		if(jm==null) jm = new JobMonitor();
		return jm;
	}
	
	public JobStatus getJobStatus(String key){
		JobStatus js = null;
		synchronized (jobs) {
			js = jobs.get(key);
			if(js== null){
				js = new JobStatus();
				js.setId(key);
				jobs.put(key, js);
			}
		}
		return js;
	}
	
	public void remove(String key){
		synchronized (jobs) {
			jobs.remove(key);
		}
	}
}
