/**
 * 
 */
package com.metarnet.hc.jobs;

/**
 * @author liuhy
 */
public enum JStatus {

	START("开始"),
	RUNNING("运行中"),
	FINISHED("结束");
	
	private JStatus(String description){
		this.description = description;
	}
	
	private String description;
	
	public String getDescription(){
		return description;
	}
}
