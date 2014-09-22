/**
 * 
 */
package com.metarnet.hc.net;

import com.metarnet.hc.net.tcp.TcpSender;

/**
 * @author liuhy
 */
public class SenderFactory {
	
	private ISender sender = null;
	
	private static SenderFactory sf = null;
	public static synchronized SenderFactory getInstance(){
		if(sf == null) sf = new SenderFactory();
		return sf;
	} 
	
	private SenderFactory(){
		SenderConfigurer.init();
		sender = new TcpSender();
	}
	
	public ISender getSender(){
		return sender;
	}
	
	public ISender getSender(String name){
		//TODO support various protocols, tcp is the default protocol  
		return null;
	}
}
