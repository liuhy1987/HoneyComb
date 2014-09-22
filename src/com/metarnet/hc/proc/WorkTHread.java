/**
 * 
 */
package com.metarnet.hc.proc;

import com.metarnet.hc.jobs.IJob;
import com.metarnet.hc.jobs.JStatus;
import com.metarnet.hc.jobs.JobMonitor;
import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.util.Constant;
import com.metarnet.hc.util.LogUtil;


/**
 * @author liuhy
 */
public class WorkTHread extends Thread {
	
	private boolean isrunning = true;
	private IAlmWorker parent;
	private IJob job;

	public WorkTHread(String name, IAlmWorker parent) {
		super(name);
		this.parent = parent;
		job = parent.getJob();
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted() && isrunning){
			AlmMsg msg = null;
			AlmMsg msgtemp = null;
			try {
				//get alarm to process
				msg = parent.getData();
				//discard msg when it fails after MSGRETRYTIMES retries to process
				if(msg.getRetrytimes()>Constant.MSGRETRYTIMES){
					LogUtil.getMsgDiscardLogger().info(msg.toJson());
					continue;
				}
				// in case of failure, clone alarmmsg to return msg to queue in need
				msgtemp = msg.clone();
				
				IJob current = job;
				JobMonitor.getInstance().getJobStatus(msg.getId()).setStatus(JStatus.START);
				do{
					LogUtil.getProcLogger().info("[process data chain]"+current.getName());
					// process msg
					msg = current.process(msg);
					// route msg to next process
					current = current.getNext();
					
					JobMonitor.getInstance().getJobStatus(msg.getId()).setStatus(JStatus.RUNNING);
				}while(current != null);
				
				JobMonitor.getInstance().getJobStatus(msg.getId()).setStatus(JStatus.FINISHED);
			} catch (Exception e) {
				LogUtil.getProcLogger().error("[process error]", e);
				LogUtil.getProcLogger().debug("return message to queue");
				//return failure msg to process queue and add one to retry-times
				msgtemp.setRetrytimes(msgtemp.getRetrytimes()+1);
				parent.add(msgtemp);
			}finally{
				//delete temp data
				JobMonitor.getInstance().remove(msg.getId());
				msgtemp = null;
				msg = null;
			}
		}
	}

	public void shutdown() {
		isrunning = false;
		interrupt();
	}

}
