/**
 * 
 */
package com.metarnet.hc.util;

import java.util.Properties;


/**
 * @author liuhy
 */
public class Constant {

	/** current alarms of store key in context */
	public static final String CURRENTALARMS = "cur_alms";
	/** identity of report alarm */
	public static String RT = "rt";
	/** identity of sync alarm */ 
	public static String SYNC = "sync";
	/** thread num in rt process per vendor */
	public static int RTWORKTHREADNUM = 1;
	/** thread num in sync process per vendor */
	public static int SYNCWORKTHREADNUM = 1;
	/** concurrent sync jobs num */
	public static int SYNCSIZE = 5;
	/** alms pack size */
	public static int SENDERALMSSIZE=50;
	/** resend times */
	public static int RESENDTIMES = 3;
	/** retry to process times */
	public static int MSGRETRYTIMES = 3;
	
	public static String host = "127.0.0.1";
	public static int port = 8081;
	/** FLICKPERIOD unit:h */
	public static int FLICKPERIOD = 12;
	/** max string length of decode and encode */
	public static int MAXPACKSIZE = 100000;
	/** size of send queue */
	public static int SENDQUEUESIZE = 10000;
	/** size of realtime alarm queue */
	public static int RTALMQUEUESIZE = 10000;
	
	public static void init() {
		Properties props = FileUtil.loadConf("conf/conf.properties");
		host = props.getProperty("host");
		port = Integer.valueOf(props.getProperty("port"));
		FLICKPERIOD = Integer.valueOf(props.getProperty("flickperiod"));
	}

}
