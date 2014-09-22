/**
 * 
 */
package com.metarnet.hc.jobs.impl;

import java.util.List;
import java.util.UUID;

import com.metarnet.hc.jobs.AbsJob;
import com.metarnet.hc.jobs.IJob;
import com.metarnet.hc.model.AlarmModel;
import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.util.DateUtil;
import com.metarnet.hc.util.LogUtil;
import com.metarnet.hc.util.ObjectUtil;

/**
 * @author liuhy
 */
public class AlarmPreProcessJob extends AbsJob {

	public AlarmPreProcessJob(IJob next) {
		super(next);
	}
	
	@Override
	public AlmMsg process(AlmMsg data) throws Exception{
		
		List<AlarmModel> alms = data.getAlms();
		for(AlarmModel am : alms){
			
			/** set an unique key */
			am.setAlarmnumber(UUID.randomUUID().toString());
			
			/** set clear alarm */
			if(am.getAlarmlevel().equals("165")){
				am.setStatus("delete");
			}
			
			/** format time */
			am.setAlarmnetime(DateUtil.formatDate(am.getAlarmnetime()));
			am.setAlarmemstime(DateUtil.formatDate(am.getAlarmemstime()));
			
			/** process alarm description */
			String alarmdesc = am.getAlarmdesc();
			String[] ctpanddesc = alarmdesc.split("\\[\\-\\]");
			am.setAlarmdesc(ctpanddesc[0]);
			if(ctpanddesc.length==2){
				am.getSnunit().setTimeslot(ctpanddesc[1]);
			}else{
				am.getSnunit().setTimeslot("");
			}
			
			/** set alarm id */
			am.setAlarmid(ObjectUtil.getAlarmID(am));
			
		}
		LogUtil.getProcLogger().info("AlarmPreProcessJob done");
		
		return data;
	}
	
	@Override
	public String getName() {
		return "AlarmPreProcessJob";
	}

}
