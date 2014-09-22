/**
 * 
 */
package com.metarnet.hc.proc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.metarnet.hc.model.AlarmModel;
import com.metarnet.hc.model.AlmMsg;

/**
 * @author mmm
 *
 */
public class SyncAlarmMsgTest {

	@Test
	public void testSort(){
		List<Integer> test = new ArrayList<Integer>();
		test.add(1);
		test.add(10);
		test.add(3);
		
		Collections.sort(test);
		
		for(int i : test){
			System.out.println(i);
		}
	}
	
	@Test
	public void SyncAlarmWorkerTest(){
		SyncAlmWorker saw = new SyncAlmWorker("aaa");
		for(int i=11; i>0; i--){
			AlmMsg msg = new AlmMsg();
			msg.setDomain("aaa");
			msg.setTotal(10);
			msg.setId("111111");
			msg.setType("SYNC");
			msg.setIndex(i);
			msg.setAlms(new ArrayList<AlarmModel>());
			saw.add(msg);
		}
	}
	
}
