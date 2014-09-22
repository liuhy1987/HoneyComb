/**
 * 
 */
package com.metarnet.hc.log;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author liuhy
 */
public class TestLog {

	@Test
	public void testlog(){
		Logger.getLogger(TestLog.class).error("bbbbb");
		Logger.getLogger("proc").debug("aaaaa");
	}
	
}
