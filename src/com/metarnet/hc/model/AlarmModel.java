/**
 * 
 */
package com.metarnet.hc.model;

/**
 * @author liuhy
 */
public class AlarmModel {

	/** uuid */
	private String alarmnumber;
	/** alarm identify */
	private String alarmid;
	
	/** two fields are not used */
	private String nativeemsname;
	private String probename;
	
	private String restype;
	private SnUnit snunit;
	private String alarmlevel;
	private String alarmcause;
	private String alarmdesc;
	private String alarmnetime; //starttime endtime
	private String alarmemstime; 
	private String blob; //remark
	private String status = "insert";
	
	/** other fields */
	private String ackperson;
	private String acktime;
	private String flashtimes;
	private String root_status;
	private String resource_state;
	
	/**
	 * @return the nativeemsname
	 */
	public String getNativeemsname() {
		return nativeemsname;
	}
	/**
	 * @param nativeemsname the nativeemsname to set
	 */
	public void setNativeemsname(String nativeemsname) {
		this.nativeemsname = nativeemsname;
	}
	/**
	 * @return the restype
	 */
	public String getRestype() {
		return restype;
	}
	/**
	 * @param restype the restype to set
	 */
	public void setRestype(String restype) {
		this.restype = restype;
	}
	/**
	 * @return the snunit
	 */
	public SnUnit getSnunit() {
		return snunit;
	}
	/**
	 * @param snunit the snunit to set
	 */
	public void setSnunit(SnUnit snunit) {
		this.snunit = snunit;
	}
	/**
	 * @return the alarmlevel
	 */
	public String getAlarmlevel() {
		return alarmlevel;
	}
	/**
	 * @param alarmlevel the alarmlevel to set
	 */
	public void setAlarmlevel(String alarmlevel) {
		this.alarmlevel = alarmlevel;
	}
	/**
	 * @return the alarmcause
	 */
	public String getAlarmcause() {
		return alarmcause;
	}
	/**
	 * @param alarmcause the alarmcause to set
	 */
	public void setAlarmcause(String alarmcause) {
		this.alarmcause = alarmcause;
	}
	/**
	 * @return the alarmdesc
	 */
	public String getAlarmdesc() {
		return alarmdesc;
	}
	/**
	 * @param alarmdesc the alarmdesc to set
	 */
	public void setAlarmdesc(String alarmdesc) {
		this.alarmdesc = alarmdesc;
	}
	/**
	 * @return the alarmnetime
	 */
	public String getAlarmnetime() {
		return alarmnetime;
	}
	/**
	 * @param alarmnetime the alarmnetime to set
	 */
	public void setAlarmnetime(String alarmnetime) {
		this.alarmnetime = alarmnetime;
	}
	/**
	 * @return the alarmemstime
	 */
	public String getAlarmemstime() {
		return alarmemstime;
	}
	/**
	 * @param alarmemstime the alarmemstime to set
	 */
	public void setAlarmemstime(String alarmemstime) {
		this.alarmemstime = alarmemstime;
	}
	/**
	 * @return the probename
	 */
	public String getProbename() {
		return probename;
	}
	/**
	 * @param probename the probename to set
	 */
	public void setProbename(String probename) {
		this.probename = probename;
	}
	/**
	 * @return the blob
	 */
	public String getBlob() {
		return blob;
	}
	/**
	 * @param blob the blob to set
	 */
	public void setBlob(String blob) {
		this.blob = blob;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param alarmnumber the alarmnumber to set
	 */
	public void setAlarmnumber(String alarmnumber) {
		this.alarmnumber = alarmnumber;
	}
	/**
	 * @return the alarmnumber
	 */
	public String getAlarmnumber() {
		return alarmnumber;
	}
	/**
	 * @param alarmid the alarmid to set
	 */
	public void setAlarmid(String alarmid) {
		this.alarmid = alarmid;
	}
	/**
	 * @return the alarmid
	 */
	public String getAlarmid() {
		return alarmid;
	}
	/**
	 * @return the ackperson
	 */
	public String getAckperson() {
		return ackperson;
	}
	/**
	 * @param ackperson the ackperson to set
	 */
	public void setAckperson(String ackperson) {
		this.ackperson = ackperson;
	}
	/**
	 * @return the acktime
	 */
	public String getAcktime() {
		return acktime;
	}
	/**
	 * @param acktime the acktime to set
	 */
	public void setAcktime(String acktime) {
		this.acktime = acktime;
	}
	/**
	 * @return the flashtimes
	 */
	public String getFlashtimes() {
		return flashtimes;
	}
	/**
	 * @param flashtimes the flashtimes to set
	 */
	public void setFlashtimes(String flashtimes) {
		this.flashtimes = flashtimes;
	}
	/**
	 * @return the root_status
	 */
	public String getRoot_status() {
		return root_status;
	}
	/**
	 * @param rootStatus the root_status to set
	 */
	public void setRoot_status(String rootStatus) {
		root_status = rootStatus;
	}
	/**
	 * @return the resource_state
	 */
	public String getResource_state() {
		return resource_state;
	}
	/**
	 * @param resourceState the resource_state to set
	 */
	public void setResource_state(String resourceState) {
		resource_state = resourceState;
	}
	
}
