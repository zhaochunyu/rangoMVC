package com.yeepay.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * 当前的类是一个user的持久化类，它映射到数据库rango中的表rangodata
 * 
 * @Table(name = "rangodata", catalog = "rango") 在实体名和表名相同的情况下可以省略（忽略大小写）
 * 
 * uniqueConstraints = {@UniqueConstraint(columnNames={"id"})} 设置唯一索引
 * 
 * @author chunyu.zhao
 *
 */
@Entity
@Table(name = "rangodata", catalog = "rango", uniqueConstraints = {@UniqueConstraint(columnNames={"id"})})
public class Rangodata implements Serializable{

	private static final long serialVersionUID = 1407724942430L;
	private Integer id;
	private Integer port;
	private String URL;
	private String returnmsg;
	private Date createTime;
	private Date updateTime;
	
	// 主键 ：@Id    主键生成方式：strategy = "increment"
	//映射表中id这个字段，不能为空，并且是唯一的
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "port", unique = true, nullable = false)
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	@Column(name = "URL", length = 250)
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	@Column(name = "returnmsg", length = 50)
	public String getReturnmsg() {
		return returnmsg;
	}
	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
