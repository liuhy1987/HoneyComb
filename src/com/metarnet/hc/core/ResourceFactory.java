/**
 * 
 */
package com.metarnet.hc.core;

import com.metarnet.hc.db.DbUtil;
import com.metarnet.hc.proc.ProcessorPool;
import com.metarnet.hc.protocol.IDecoder;
import com.metarnet.hc.protocol.IEncoder;
import com.metarnet.hc.protocol.impl.MsgDecoder;
import com.metarnet.hc.protocol.impl.MsgEncoder;

/**
 * 公共资源管理
 * @author liuhy
 */
public class ResourceFactory {

	private static DbUtil db = null;
	private static IDecoder decoder = null;
	private static IEncoder encoder = null;
	private static ProcessorPool pp = null;
	
	public static void init() throws Exception{
		db = new DbUtil();
		decoder = new MsgDecoder();
		encoder = new MsgEncoder();
		pp = new ProcessorPool();
	}

	public static DbUtil getDb() {
		return db;
	}

	public static IDecoder getDecoder() {
		return decoder;
	}

	public static IEncoder getEncoder() {
		return encoder;
	}
	
	public static ProcessorPool getProcPool(){
		return pp;
	}
	
}
