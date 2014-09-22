/**
 * 
 */
package com.metarnet.hc.msg;

import junit.framework.Assert;

import org.junit.Test;

import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.protocol.IDecoder;
import com.metarnet.hc.protocol.impl.MsgDecoder;

/**
 * @author liuhy
 */
public class MsgDecoderTest {

	@Test
	public void testDecoder(){
		AlmMsg ls=new AlmMsg();
		for(int i=0; i<5; i++){
		IDecoder decoder = new MsgDecoder();
		ls = decoder.decode(
				"[{\"blob\":\"/sts3c_au4-j=1\",\"nativeemsname\":\"muju\",\"snunit\":{\"slotno\":\"5\",\"subslotno\":\"\",\"sysno\":\"muju\",\"shelfno\":\"1\",\"cardno\":\"1\",\"portno\":\"2\",\"neno\":\"79(P)\",\"subshelfno\":\"\"},\"alarmdesc\":\"Unknown[-]/sts3c_au4-j=1\",\"alarmnetime\":1369189588000,\"alarmemstime\":1369189588000,\"alarmcause\":\"不可用秒开始(UAS)\",\"alarmlevel\":162,\"restype\":10006,\"probename\":\"\"}," +
				"{\"blob\":\"/sts3c_au4-j=1\",\"nativeemsname\":\"muju\",\"snunit\":{\"slotno\":\"5\",\"subslotno\":\"\",\"sysno\":\"muju\",\"shelfno\":\"1\",\"cardno\":\"1\",\"portno\":\"3\",\"neno\":\"79(P)\",\"subshelfno\":\"\"},\"alarmdesc\":\"Unknown[-]/sts3c_au4-j=1\",\"alarmnetime\":1369189588000,\"alarmemstime\":1369189588000,\"alarmcause\":\"不可用秒开始(UAS)\",\"alarmlevel\":161,\"restype\":10006,\"probename\":\"\"}," +
				"{\"blob\":\"/sts3c_au4-j=1\",\"nativeemsname\":\"muju\",\"snunit\":{\"slotno\":\"5\",\"subslotno\":\"\",\"sysno\":\"muju\",\"shelfno\":\"1\",\"cardno\":\"1\",\"portno\":\"4\",\"neno\":\"79(P)\",\"subshelfno\":\"\"},\"alarmdesc\":\"Unknown[-]/sts3c_au4-j=1\",\"alarmnetime\":1369189588000,\"alarmemstime\":1369189588000,\"alarmcause\":\"不可用秒开始(UAS)\",\"alarmlevel\":163,\"restype\":10006,\"probename\":\"\"}," +
				"{\"blob\":\"/sts3c_au4-j=1\",\"nativeemsname\":\"muju\",\"snunit\":{\"slotno\":\"5\",\"subslotno\":\"\",\"sysno\":\"muju\",\"shelfno\":\"1\",\"cardno\":\"1\",\"portno\":\"5\",\"neno\":\"79(P)\",\"subshelfno\":\"\"},\"alarmdesc\":\"Unknown[-]/sts3c_au4-j=1\",\"alarmnetime\":1369189588000,\"alarmemstime\":1369189588000,\"alarmcause\":\"不可用秒开始(UAS)\",\"alarmlevel\":164,\"restype\":10006,\"probename\":\"\"}," +
				"{\"blob\":\"/sts3c_au4-j=1\",\"nativeemsname\":\"muju\",\"snunit\":{\"slotno\":\"5\",\"subslotno\":\"\",\"sysno\":\"muju\",\"shelfno\":\"1\",\"cardno\":\"1\",\"portno\":\"6\",\"neno\":\"79(P)\",\"subshelfno\":\"\"},\"alarmdesc\":\"Unknown[-]/sts3c_au4-j=1\",\"alarmnetime\":1369189588000,\"alarmemstime\":1369189588000,\"alarmcause\":\"不可用秒开始(UAS)\",\"alarmlevel\":161,\"restype\":10006,\"probename\":\"\"}," +
				"{\"blob\":\"/sts3c_au4-j=1\",\"nativeemsname\":\"muju\",\"snunit\":{\"slotno\":\"5\",\"subslotno\":\"\",\"sysno\":\"muju\",\"shelfno\":\"1\",\"cardno\":\"1\",\"portno\":\"7\",\"neno\":\"79(P)\",\"subshelfno\":\"\"},\"alarmdesc\":\"Unknown[-]/sts3c_au4-j=1\",\"alarmnetime\":1369189588000,\"alarmemstime\":1369189588000,\"alarmcause\":\"不可用秒开始(UAS)\",\"alarmlevel\":163,\"restype\":10006,\"probename\":\"\"}]");
//		Assert.assertEquals(ls.getIndex(), "162");
		}
		Assert.assertNull(ls);
	}
	
	@Test
	public void testSplit(){
		String str = "LP-AIS[-]/sts3c_au4-j=2/vt2_tu12-k=1-l=4-m=3";
		String[] ret = str.split("\\[\\-\\]");
		System.out.println(ret[1]);
	}
	
}
