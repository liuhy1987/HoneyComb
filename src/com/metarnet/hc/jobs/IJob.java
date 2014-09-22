/**
 * 
 */
package com.metarnet.hc.jobs;

import com.metarnet.hc.model.AlmMsg;


/**
 * @author liuhy
 */
public interface IJob {
	
	public String getName();

	/**
	 * 处理数据
	 * @param nextproc
	 * @param data
	 * @throws Exception 
	 */
	public AlmMsg process(AlmMsg data) throws Exception;
	
	/***
	 * 获取下个处理
	 * @return
	 */
	public IJob getNext();
	
}
