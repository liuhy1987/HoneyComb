/**
 * 
 */
package com.metarnet.hc.proc;

import com.metarnet.hc.jobs.IJob;
import com.metarnet.hc.model.AlmMsg;

/**
 * @author liuhy
 */
public interface IAlmWorker {

	public IJob getJob();
	
	public AlmMsg getData() throws InterruptedException;

	public void add(AlmMsg msgtemp);
	
}
