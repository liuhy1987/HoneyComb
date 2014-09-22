package com.metarnet.hc.db;

import java.util.HashMap;
import java.util.Map;

public class DbStatus {

	private Map<String, Integer> sum = new HashMap<String, Integer>();
	private boolean exception = false;
	
	
	/**
	 * @return the sum
	 */
	public int getSum(String type) {
		return sum.get(type)==null?0:sum.get(type);
	}
	/**
	 * @param sum the sum to set
	 */
	public void setSum(String type, int value) {
		this.sum.put(type, value);
	}
	/**
	 * @return the exception
	 */
	public boolean isException() {
		return exception;
	}
	/**
	 * @param exception the exception to set
	 */
	public void setException(boolean exception) {
		this.exception = exception;
	}
	
}
