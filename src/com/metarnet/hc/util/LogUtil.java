/**
 * 
 */
package com.metarnet.hc.util;

import org.apache.log4j.Logger;

/**
 * @author liuhy
 */
public class LogUtil {

	public static Logger getProcLogger(){
		return Logger.getLogger("proc");
	}
	
	public static Logger getRecvLogger(){
		return Logger.getLogger("recv");
	}
	
	public static Logger getMsgLogger(){
		return Logger.getLogger("msg");
	}
	
	public static Logger getStoreLogger(){
		return Logger.getLogger("store");
	}
	
	public static Logger getSendLogger(){
		return Logger.getLogger("send");
	}
	
	public static Logger getInitLogger(){
		return Logger.getLogger("init");
	}
	
	public static Logger getMsgDiscardLogger(){
		return Logger.getLogger("msgdiscard");
	}
	
}
