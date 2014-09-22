/**
 * 
 */
package com.metarnet.hc.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.metarnet.hc.util.Constant;

/**
 * @author liuhy
 */
public class NioServer {

	/***
	 * 启动接收服务
	 * @param ip 
	 * @param port 
	 * @throws IOException 
	 */
	public void startServer(String ip, int port) throws IOException {
		IoAcceptor acceptor = new NioSocketAcceptor();

//		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		TextLineCodecFactory text = new TextLineCodecFactory(Charset.forName("UTF-8"));
		text.setDecoderMaxLineLength(Constant.MAXPACKSIZE);
		text.setEncoderMaxLineLength(Constant.MAXPACKSIZE);
		acceptor.getFilterChain().addLast("codc", new ProtocolCodecFilter(text));
		acceptor.setHandler(new NioHandler());

		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		
		acceptor.bind(new InetSocketAddress(ip, port));
	}

}
