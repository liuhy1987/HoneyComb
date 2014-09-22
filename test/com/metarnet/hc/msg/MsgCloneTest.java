/**
 * 
 */
package com.metarnet.hc.msg;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.metarnet.hc.model.AlarmModel;
import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.model.SnUnit;

/**
 * @author mmm
 *
 */
public class MsgCloneTest {

	@Test
	public void testClone(){
		AlmMsg msg = new AlmMsg();
		msg.setDomain("ZTE/haixi");
		msg.setId("1");
		msg.setIndex(1);
		msg.setTotal(1);
		msg.setType("rt");
		
		List<AlarmModel> alms = new ArrayList<AlarmModel>();
		AlarmModel am = new AlarmModel();
		am.setAlarmcause("信号丢失(PDH-LOS)");
		am.setAlarmdesc("PDH-LOS");
		am.setAlarmemstime("0");
		am.setAlarmnetime("1373333327000");
		am.setAlarmlevel("162");
		am.setRestype("10006");
		SnUnit sn = new SnUnit();
		sn.setSysno("ZTE/haixi");
		sn.setNeno("976(P)");
		sn.setShelfno("1");
		sn.setSlotno("1");
		sn.setCardno("1");
		sn.setPortno("2");
		am.setSnunit(sn);
		alms.add(am);
		msg.setAlms(alms);
		
		AlmMsg msgclone = msg.clone();
		msg.setType("aaaaaaaaaaaaaaa");
		msg.getAlms().get(0).setAlarmemstime("1111111");
		Assert.assertEquals(msgclone.getType(),"rt");
		Assert.assertEquals(msgclone.getAlms().get(0).getAlarmemstime(),"0");
	}
	
}
