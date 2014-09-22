/**
 * 
 */
package com.metarnet.hc.jobs.impl;

import java.util.ArrayList;
import java.util.List;

import com.metarnet.hc.core.ResourceFactory;
import com.metarnet.hc.db.DbStatus;
import com.metarnet.hc.jobs.AbsJob;
import com.metarnet.hc.jobs.IJob;
import com.metarnet.hc.model.AlarmModel;
import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.util.LogUtil;

/**
 * @author liuhy
 */
public class StoreAlarmsJob extends AbsJob {

	public StoreAlarmsJob(IJob next) {
		super(next);
	}

	/* (non-Javadoc)
	 * @see com.metarnet.hc.jobs.IJob#getName()
	 */
	@Override
	public String getName() {
		return "StoreAlarmsJob";
	}

	/* (non-Javadoc)
	 * @see com.metarnet.hc.jobs.IJob#process(com.metarnet.hc.model.AlmMsg)
	 */
	@Override
	public AlmMsg process(AlmMsg data) throws Exception {
		LogUtil.getProcLogger().info("store alarm " + data.toJson());
		//store alarm
		DbStatus flag = ResourceFactory.getDb().storeAlarms(data);
		
		if(!flag.isException() && flag.getSum("delete")!=0){
			LogUtil.getProcLogger().info("clear alarms...");
			// clear all cleared alarm
			ResourceFactory.getDb().clearAlarms();
		}else if(!flag.isException() && flag.getSum("insert")!=0){
			//do something
		}else{
			throw new Exception("store failed");
		}
		
		//update alarm will be not sent
		List<AlarmModel> alms = new ArrayList<AlarmModel>();
		for(AlarmModel alm : data.getAlms()){
			if(alm.getStatus().equals("update")){
				continue;
			}
			alms.add(alm);
		}
		//add changed alarms except update
		data.setAlms(alms);
		
		LogUtil.getProcLogger().info("StoreAlarmsJob done");
		LogUtil.getProcLogger().info("send non-update alarms " + data.toJson());
		
		return data;
	}
	
}
