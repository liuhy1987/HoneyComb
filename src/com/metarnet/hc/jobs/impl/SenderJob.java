/**
 * 
 */
package com.metarnet.hc.jobs.impl;

import com.metarnet.hc.jobs.AbsJob;
import com.metarnet.hc.jobs.IJob;
import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.net.SenderFactory;
import com.metarnet.hc.util.LogUtil;

/**
 * @author liuhy
 */
public class SenderJob extends AbsJob {

	public SenderJob(IJob next) {
		super(next);
	}
	
	@Override
	public AlmMsg process(AlmMsg data) throws Exception{
		if(data.getAlms().size()>0){
			SenderFactory.getInstance().getSender().send(data);
			LogUtil.getProcLogger().info("SenderJob done");
		}
		return data;
	}
	
	@Override
	public String getName() {
		return "SenderJob";
	}

}
