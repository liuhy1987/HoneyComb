/**
 * 
 */
package com.metarnet.hc.model;

import java.util.List;

import net.minidev.json.JSONValue;

/**
 * @author liuhy
 */
public class AlmMsg {
	
	private String id;
	private String domain;
	private String type;
	private int total;
	private int index;
	private int retrytimes=0;
	List<AlarmModel> alms;

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
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the totle
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param totle the totle to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the alms
	 */
	public List<AlarmModel> getAlms() {
		return alms;
	}

	/**
	 * @param alms the alms to set
	 */
	public void setAlms(List<AlarmModel> alms) {
		this.alms = alms;
	}
	
	public String toJson(){
		return JSONValue.toJSONString(this);
	}

	public AlmMsg clone(){
		return JSONValue.parse(toJson(),AlmMsg.class);
	}

	/**
	 * @param retrytimes the retrytimes to set
	 */
	public void setRetrytimes(int retrytimes) {
		this.retrytimes = retrytimes;
	}

	/**
	 * @return the retrytimes
	 */
	public int getRetrytimes() {
		return retrytimes;
	}
}
