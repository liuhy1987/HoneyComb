/**
 * 
 */
package com.metarnet.hc.protocol;

import com.metarnet.hc.model.AlmMsg;
public interface IDecoder {

	public AlmMsg decode(Object obj);
	
}
