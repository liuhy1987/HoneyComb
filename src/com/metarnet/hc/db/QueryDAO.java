/**
 * 
 */
package com.metarnet.hc.db;

import java.util.List;
import java.util.Map;

import com.metarnet.hc.model.AlarmModel;

/**
 * @author liuhy
 */
public interface QueryDAO {

	public List<AlarmModel> getCurrentAlarmsBySys(String domain);
	
	public List<AlarmModel> queryClearAlarms();
	
	public List<AlarmModel> getFlickAlarms(Map<String, String> map);
	
}
