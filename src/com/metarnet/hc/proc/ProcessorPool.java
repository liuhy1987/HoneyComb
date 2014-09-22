/**
 * 
 */
package com.metarnet.hc.proc;

import java.util.HashMap;
import java.util.Map;

import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.util.Constant;
import com.metarnet.hc.util.LogUtil;


/**
 * @author liuhy
 */
public class ProcessorPool {

	private Map<String, ReportAlmWorker> rtalm = new HashMap<String, ReportAlmWorker>();
	private Map<String, SyncAlmWorker> syncalm = new HashMap<String, SyncAlmWorker>();
	
	public void dispath(AlmMsg msg){
		if(msg.getType().equalsIgnoreCase(Constant.RT)){ //rt alarm
			//get alarm processor from map by sysno
			ReportAlmWorker rtalmworker = rtalm.get(msg.getDomain());
			if(rtalmworker == null){
				synchronized (rtalm) {
					rtalmworker = rtalm.get(msg.getDomain());
					if(rtalmworker == null){
						rtalmworker = new ReportAlmWorker(msg.getDomain());
						rtalm.put(msg.getDomain(), rtalmworker);
						LogUtil.getRecvLogger().info("[new rt sysno]" + msg.getDomain());
					}
				}
				rtalmworker.add(msg);
				LogUtil.getRecvLogger().info("[add message to rt queue]" + msg.getId());
			}
		}else if(msg.getType().equalsIgnoreCase(Constant.SYNC)){ //sync alarm
			SyncAlmWorker syncalmworker = syncalm.get(msg.getDomain());
			if(syncalmworker == null){
				synchronized (syncalm) {
					syncalmworker = syncalm.get(msg.getDomain());
					if(syncalmworker == null){
						syncalmworker = new SyncAlmWorker(msg.getDomain());
						syncalm.put(msg.getDomain(), syncalmworker);
						LogUtil.getRecvLogger().info("[new sync sysno]"+msg.getDomain());
					}
				}
				syncalmworker.add(msg);
				LogUtil.getRecvLogger().info("[add message to sync queue]" + msg.getId() + ":" + msg.getIndex());
			}
		}else{
			// unknown alarm
			LogUtil.getRecvLogger().info("[unknown alarm]" + msg.getId());
		}
	}
	
	/**
	 * 移除某个厂家
	 * @param type
	 * @param key
	 */
	public void remove(String type, String key){
		if(type.equalsIgnoreCase(Constant.RT)){
			synchronized (rtalm) {
				rtalm.get(key).shutdown();
				rtalm.remove(key);
			}
		}else if(type.equalsIgnoreCase(Constant.SYNC)){
			synchronized (syncalm) {
				syncalm.get(key).shutdown();
				syncalm.remove(key);
			}
		}else{
			// unknown cmd
		}
	}
	
}
