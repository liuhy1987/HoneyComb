/**
 * 
 */
package com.metarnet.hc.model;

import java.util.concurrent.ConcurrentHashMap;

import com.metarnet.hc.jobs.JStatus;

/**
 * @author liuhy
 */
public class JobStatus {

	private String id;
	private JStatus status;
	private ConcurrentHashMap<String, Object> context = new ConcurrentHashMap<String, Object>();
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the status
	 */
	public JStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(JStatus status) {
		this.status = status;
	}
	/**
	 * @return the context
	 */
	public ConcurrentHashMap<String, Object> getContext() {
		return context;
	}
	
}
