/**
 * 
 */
package com.metarnet.hc;

import com.metarnet.hc.core.NioServer;
import com.metarnet.hc.util.Constant;
import com.metarnet.hc.util.LogUtil;

/**
 * @author liuhy
 */
public class Starter {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		//FIXME just start nioserver
		LogUtil.getInitLogger().info("*******************************");
		LogUtil.getInitLogger().info("init config");
		Constant.init();
		LogUtil.getInitLogger().info("init config finished");
		LogUtil.getInitLogger().info("init resource");
//		ResourceFactory.init();
		LogUtil.getInitLogger().info("init resource finished");
		LogUtil.getInitLogger().info("start server");
		NioServer server = new NioServer();
		server.startServer(Constant.host, Constant.port);
		LogUtil.getInitLogger().info("start server finished");
		LogUtil.getInitLogger().info("*******************************");
	}

}
