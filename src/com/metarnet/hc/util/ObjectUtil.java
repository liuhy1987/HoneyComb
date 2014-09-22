/**
 * 
 */
package com.metarnet.hc.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.metarnet.hc.model.AlarmModel;
import com.metarnet.hc.model.SnUnit;

/**
 * @author liuhy
 */
public class ObjectUtil {

	public static Map<String, String> alarmModel2Map(AlarmModel am){
		Map<String, String> ret = new HashMap<String, String>();
		
		Field[] fields=am.getClass().getDeclaredFields();  
		for(int i=0; i<fields.length; i++){
			fields[i].setAccessible(true);
			String key = fields[i].getName();
			Object value = null;
			try {
				value = fields[i].get(am);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(value != null){
				if(value instanceof SnUnit){
					addSnunit(value, ret);
				}else{
					ret.put(key, value.toString());
				}
			}else{
				ret.put(key, "");
			}
			fields[i].setAccessible(false);
		}
		
		return ret;
	}

	private static void addSnunit(Object value, Map<String, String> ret) {
		Field[] fields=value.getClass().getDeclaredFields();  
		for(int i=0; i<fields.length; i++){
			fields[i].setAccessible(true);
			String key = fields[i].getName();
			Object val = null;
			try {
				val = fields[i].get(value);
			} catch (Exception e) {
				
			}
			if(val != null){
				ret.put(key, val.toString());
			}else{
				ret.put(key, "");
			}
			fields[i].setAccessible(false);
		}
	}

	public static boolean compareAlarmModel(AlarmModel am, AlarmModel curalm) {
		if(am.getAlarmid().equals(curalm.getAlarmid()) 
				&& am.getAlarmnetime().equals(curalm.getAlarmnetime())){
			return true;
		}
		return false;
	}

	public static String getAlarmID(AlarmModel am) {
		String seperate = "[+]";
		StringBuilder sb = new StringBuilder();
		sb.append(am.getSnunit().getSysno()).append(seperate)
		.append(am.getSnunit().getNeno()).append(seperate)
		.append(am.getSnunit().getShelfno()).append(seperate)
		.append(am.getSnunit().getSubshelfno()).append(seperate)
		.append(am.getSnunit().getSlotno()).append(seperate)
		.append(am.getSnunit().getSubslotno()).append(seperate)
		.append(am.getSnunit().getCardno()).append(seperate)
		.append(am.getSnunit().getPortno()).append(seperate)
		.append(am.getSnunit().getTimeslot()).append(seperate)
		.append(am.getAlarmcause()).append(seperate)
		.append(am.getAlarmdesc());
//		.append(seperate).append(am.getAlarmlevel());
		return sb.toString();
	}
	
}
