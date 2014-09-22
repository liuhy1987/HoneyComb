/**
 * 
 */
package com.metarnet.hc.net;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import com.metarnet.hc.model.SenderDest;
import com.metarnet.hc.util.FileUtil;
import com.metarnet.hc.util.LogUtil;

/**
 * @author liuhy
 */
public class SenderConfigurer {

	private static final String ROOTPREFIX = "sender.root";
	private static final String INSTANCEPREFIX = "sender.instance.";
	private static List<SenderDest> all = new ArrayList<SenderDest>();
	
	public static List<SenderDest> getAllDestination() {
		return all;
	}
	
	public static void init(){
		Properties props = FileUtil.loadConf("conf/sendtoconf.properties");
		extractSenderDest(props);
	}
	
	private static void extractSenderDest(Properties props) {
		String root = props.getProperty(ROOTPREFIX);
		StringTokenizer st = new StringTokenizer(root, ",");
		while (st.hasMoreTokens()) {
			String instanceName = st.nextToken().trim();
			if ((instanceName == null) || (instanceName.equals(",")))
				continue;
			addDest(props, instanceName);
		}
		
	}

	private static void addDest(Properties props, String instanceName) {
		String host = props.getProperty(INSTANCEPREFIX+instanceName+".host");
		String port = props.getProperty(INSTANCEPREFIX+instanceName+".port");
		String timeout = props.getProperty(INSTANCEPREFIX+instanceName+".timeout","180000");
		if(host!=null&&host!=""&&port!=null&&port!=""){
			SenderDest sd = new SenderDest();
			sd.setIp(host);
			sd.setName(instanceName);
			sd.setPort(port);
			sd.setTimeout(Integer.valueOf(timeout));
			all.add(sd);
			LogUtil.getSendLogger().info("[Destination]" + host + ":" + port);
		}
	}

}
