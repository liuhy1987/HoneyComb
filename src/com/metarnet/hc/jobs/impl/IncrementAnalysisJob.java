/**
 * 
 */
package com.metarnet.hc.jobs.impl;

import java.util.ArrayList;
import java.util.List;

import com.metarnet.hc.jobs.AbsJob;
import com.metarnet.hc.jobs.IJob;
import com.metarnet.hc.jobs.JobMonitor;
import com.metarnet.hc.model.AlarmModel;
import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.util.Constant;
import com.metarnet.hc.util.LogUtil;
import com.metarnet.hc.util.ObjectUtil;

/**
 * @author liuhy
 */
public class IncrementAnalysisJob extends AbsJob {

	public IncrementAnalysisJob(IJob next) {
		super(next);
	}

	@Override
	public AlmMsg process(AlmMsg data) throws Exception{
		
		@SuppressWarnings("unchecked")
		List<AlarmModel> curalms = (ArrayList<AlarmModel>) JobMonitor.getInstance().getJobStatus(data.getId()).getContext().get(Constant.CURRENTALARMS);
		List<AlarmModel> alms = data.getAlms();
		
		LogUtil.getProcLogger().info("[IncrementAnalysisJob]" + curalms.size() + ":" + alms.size());
		for(AlarmModel am : alms){
			for(AlarmModel curalm : curalms){
				if(ObjectUtil.compareAlarmModel(am, curalm)){
					am.setStatus("update");
					curalm.setStatus("update");
				}
			}
		}
		LogUtil.getProcLogger().info("[IncrementAnalysisJob]delete not-exist alarm");
		for(AlarmModel curalm : curalms){
			if(curalm.getStatus().equals("delete")){
				alms.add(curalm);
			}
		}
		
		LogUtil.getProcLogger().info("IncrementAnalysisJob done");
		return data;
	}

	@Override
	public String getName() {
		return "IncrementAnalysisJob";
	}

}
