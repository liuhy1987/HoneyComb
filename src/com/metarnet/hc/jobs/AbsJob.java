/**
 * 
 */
package com.metarnet.hc.jobs;


/**
 * @author liuhy
 */
public abstract class AbsJob implements IJob {
	
	private IJob next = null;

	public AbsJob(IJob next){
		this.next = next;
	}
	
	public IJob getNext(){
		return next;
	}

}
