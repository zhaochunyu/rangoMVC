package com.yeepay.service;


import java.util.List;

import com.yeepay.entity.Rangodata;
import com.yeepay.entity.User;
import com.yeepay.model.RangodataModel;
import com.yeepay.model.UserModel;
import com.yeepay.util.page.PageResultSet;

/**
 * 业务层
 * 
 * @author chunyu.zhao
 *
 */
public interface RangodataService {

	/**
	 * 添加
	 * @param rangodata
	 */
	public void saveRango(Rangodata rangodata);
	
	/**
	 * 修改
	 * @param rangodata
	 */
	public void updateRango(Rangodata rangodata);
	
	/**
	 * 删除
	 * @param rangodata
	 */
	public void deleteRango(Rangodata rangodata);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<Rangodata> findAllRangodataList();
	
	/**
	 * 根据条件查询数据
	 * @param Rangodata
	 * @return
	 */
	public List<Rangodata> findRangodataListByCondition(Rangodata rangodata);
	
	/**
	 * 通过条件查找用户
	 * @param RangodataModel
	 * @return
	 */
	public Rangodata findRangodataByByCondition(RangodataModel rangodataModel);
	/**
	 * 通过条件查找用户
	 * @param RangodataModel
	 * @return
	 */
	public Rangodata findRangodataSByCondition(int port);
	
	/**
	 * 通过ID查询Rangodata
	 * @param id
	 * @return
	 */
	public Rangodata getRangodataById(int id);
	
	
}
