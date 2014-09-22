/**
 * 
 */
package com.metarnet.hc.proc;

import java.util.ArrayList;
import java.util.List;

import com.metarnet.hc.model.AlmMsg;

/**
 * @author liuhy
 */
public class SyncAlarmMsg {

	private int total;
	private List<Integer> packnum = new ArrayList<Integer>();
	private List<AlmMsg> alms = new ArrayList<AlmMsg>();
	
	public synchronized boolean add(AlmMsg msg){
		total = msg.getTotal();
		packnum.add(msg.getIndex());
		alms.add(msg);
		return check();
	}
	
	public boolean check(){
		boolean flag = false;
		if (total == packnum.size()) {
			flag = true;
		}
		return flag;
	}
	
	public AlmMsg getAllAlmMsg(){
		AlmMsg msg = alms.get(0);
		for(int i=1; i<alms.size(); i++){
			msg.getAlms().addAll(alms.get(i).getAlms());
		}
		return msg;
	}
	
}
