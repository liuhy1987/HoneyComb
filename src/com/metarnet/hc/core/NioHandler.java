/**
 * 
 */
package com.metarnet.hc.core;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.metarnet.hc.util.LogUtil;

/**
 * @author liuhy
 */
public class NioHandler extends IoHandlerAdapter {
	
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		LogUtil.getRecvLogger().error(cause.getMessage(), cause);
		super.exceptionCaught(session, cause);
	}
	
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		super.sessionIdle(session, status);
	}

	public void messageReceived(IoSession session, Object message)
			throws Exception {
		
		LogUtil.getMsgLogger().debug("[receive alarms]"+message);
		
		//FIXME for test, codes below would be commented
		
		/*
		//decoder
		AlmMsg msg = ResourceFactory.getDecoder().decode(message);
		//dispath
		if(msg != null){
			ResourceFactory.getProcPool().dispath(msg);
		}
		
		*/
	}
	
}
