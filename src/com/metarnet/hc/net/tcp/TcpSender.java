/**
 * 
 */
package com.metarnet.hc.net.tcp;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.model.SenderDest;
import com.metarnet.hc.net.ISender;
import com.metarnet.hc.net.SenderConfigurer;
import com.metarnet.hc.util.Constant;
import com.metarnet.hc.util.LogUtil;

/**
 * @author liuhy
 *
 */
public class TcpSender implements ISender {
	
	private SenderThread[] st = null;
	
	public TcpSender(){
		//get all destination
		List<SenderDest> dest = SenderConfigurer.getAllDestination();
		int destnum = dest.size();
		LogUtil.getSendLogger().info("init " + destnum + " tcp destination");
		// send to each destination by a independent thread
		st = new SenderThread[destnum];
		for(int i=0; i<destnum; i++){
			SenderDest sd = dest.get(i);
			st[i] = new SenderThread(sd.getIp(),sd.getPort());
			st[i].start();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.metarnet.hc.net.ISender#send(java.lang.String)
	 */
	@Override
	public void send(AlmMsg msg) {
		// add msg to all destination
		for(int i=0; i<st.length; i++){
			st[i].addToSend(msg);
		}
	}
	
	private class SenderThread extends Thread{
		
		// a cache queue
		private ArrayBlockingQueue<AlmMsg> queue = new ArrayBlockingQueue<AlmMsg>(Constant.SENDQUEUESIZE);
		// sendto socket stub
		private Socket client;
		private String ip;
		private int port;
		private boolean run = true;
		
		public SenderThread(String ip, String port) {
			this.ip = ip;
			this.port = Integer.valueOf(port);
			connect();
		}
		
		public void addToSend(AlmMsg msg){
			queue.add(msg);
		}

		private void connect() {
			try {
				client = new Socket(ip, port);
			} catch (Exception e) {
				LogUtil.getSendLogger().error("connect "+ip+":"+port+" fail", e);
			}
		}
		
		private void send(AlmMsg alm){
			int almsize = alm.getAlms().size();
			if(almsize > Constant.SENDERALMSSIZE){
				int size = almsize / Constant.SENDERALMSSIZE;
				int remainder = almsize % Constant.SENDERALMSSIZE;
				if(remainder != 0){
					size++;
				}
				
				for(int i=0; i<size; i++){
					int start = i * Constant.SENDERALMSSIZE;
					int end = (i + 1) * Constant.SENDERALMSSIZE;
					if (i == size-1) {
						end = almsize;
					}
					AlmMsg alarm = new AlmMsg();
					alarm.setId(alm.getId());
					alarm.setDomain(alm.getDomain());
					alarm.setTotal(size);
					alarm.setIndex(i+1);
					alarm.setType(alm.getType());
					alarm.setAlms(alm.getAlms().subList(start, end));
					
					sendToDest(alarm.toJson());
				}
				
			}else{
				sendToDest(alm.toJson());
			}
		}
		
		private void sendToDest(String json){
			
			boolean sendfailflag = true;
			for(int i=0; i<Constant.RESENDTIMES; i++){
				try {
					client.getOutputStream().write((json+"\r\n").getBytes("UTF-8"));
					client.getOutputStream().flush();
					LogUtil.getSendLogger().debug("[send msg success]"+ip+":"+port+"--"+json);
					sendfailflag = false;
					break;
				} catch (IOException e) {
					LogUtil.getSendLogger().debug("[send fail]"+ip+":"+port); 
					LogUtil.getSendLogger().debug("[reconnect]"+ip+":"+port);
					connect();
					LogUtil.getSendLogger().debug("[resend msg "+(i+1)+" times]"+ip+":"+port);
				}
			}
			
			if(sendfailflag){
				LogUtil.getSendLogger().error("[resend fail, stop send msg]"+json);
			}
		}

		@SuppressWarnings("unused")
		public void shutdown() {
			run = false;
			interrupt();
			if (client != null && client.isConnected()) {
				try {
					client.close();
				} catch (IOException e) {

				}
			}
		}
		
		public void run(){
			while(run){
				try {
					LogUtil.getSendLogger().info("send queue size " + queue.size());
					AlmMsg alm = queue.take();
					LogUtil.getSendLogger().info("start to send " + alm.getId());
					send(alm);
					LogUtil.getSendLogger().info("finish to send " + alm.getId());
				} catch (Exception e) {
					LogUtil.getSendLogger().error("send fail.");
				}
			}
		}
	}
	
}
