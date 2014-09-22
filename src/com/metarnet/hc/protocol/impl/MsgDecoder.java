/**
 * 
 */
package com.metarnet.hc.protocol.impl;

import net.minidev.json.JSONValue;

import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.protocol.IDecoder;
import com.metarnet.hc.util.LogUtil;

/**
 * @author liuhy
 */
public class MsgDecoder implements IDecoder{

	@Override
	public AlmMsg decode(Object obj) {
		AlmMsg msg = null;
		try{
			msg = JSONValue.parse(obj.toString(), AlmMsg.class);
		}catch(Throwable ex){
			LogUtil.getProcLogger().error("decode msg error", ex);
		}
		return msg;
	}

}
