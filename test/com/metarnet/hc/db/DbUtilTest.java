/**
 * 
 */
package com.metarnet.hc.db;

import java.util.List;

import org.junit.Test;

import net.minidev.json.JSONValue;

import com.metarnet.hc.model.AlarmModel;
import com.metarnet.hc.model.SnUnit;
import com.metarnet.hc.util.ObjectUtil;

/**
 * @author liuhy
 */
public class DbUtilTest {

	@Test
	public void testQueryAlarm() throws Exception{
		DbUtil db = new DbUtil();
		List<AlarmModel> ls = db.queryCurrAlarms("依赛光传输系统");
		for(AlarmModel am : ls){
			System.out.println(JSONValue.toJSONString(am));
		}
	}
	
	@Test
	public void testObject(){
		AlarmModel a = new AlarmModel();
		SnUnit s = new SnUnit();
		a.setSnunit(s);
		System.out.println(ObjectUtil.alarmModel2Map(a));
	}
	
}
