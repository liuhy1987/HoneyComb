/**
 * 
 */
package com.metarnet.hc.protocol.impl;

import net.minidev.json.JSONValue;

import com.metarnet.hc.protocol.IEncoder;

/**
 * @author liuhy
 */
public class MsgEncoder implements IEncoder{

	@Override
	public String encode(Object obj) {
		return JSONValue.toJSONString(obj);
	}

}
