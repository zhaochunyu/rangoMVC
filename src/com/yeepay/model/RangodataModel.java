package com.yeepay.model;

import java.io.Serializable;
import java.util.Date;

public class RangodataModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5853435112594460195L;
	private Integer id;
	private Integer port;
	private String URL;
	private String returnmsg;
	private Date createTime;
	private Date updateTime;
	private String pact;
	private String returntype; 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getReturnmsg() {
		return returnmsg;
	}
	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}
	 
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getPact() {
		return pact;
	}
	public void setPact(String pact) {
		this.pact = pact;
	}
	public String getReturntype() {
		return returntype;
	}
	public void setReturntype(String returntype) {
		this.returntype = returntype;
	}
	
}
