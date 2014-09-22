/**
 * 
 */
package com.metarnet.hc.db;

import java.util.Map;

/**
 * @author liuhy
 */
public interface AlarmDAO {
	
	public int insertAlarm(Map<String, String> map);
	
	public int insertHisAlarm(Map<String, String> map);
	
	public int updateAlarm(Map<String, String> map);
	
	public int deleteAlarm(Map<String, String> map);
	
	public int deleteAlarmCacheByTime(Map<String, String> map);

	public int updateClearAlarm(Map<String, String> map);

	public void insertAlarmCache(Map<String, String> map);
	
}
