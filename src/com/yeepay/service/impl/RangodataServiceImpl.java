package com.yeepay.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yeepay.dao.BaseDAO;
import com.yeepay.dao.impl.BaseDAOImpl;
import com.yeepay.entity.Rangodata;
import com.yeepay.model.RangodataModel;
import com.yeepay.service.RangodataService;
import com.yeepay.util.Util;

@Service("rangodataService")
public class RangodataServiceImpl implements RangodataService {

	@Resource
	private BaseDAOImpl<Rangodata> rangodataDAO;
 
	@Override
	public void saveRango(Rangodata rangodata) {
		// TODO Auto-generated method stub
		rangodataDAO.save(rangodata)	;
	}

	@Override
	public void updateRango(Rangodata rangodata) {
		// TODO Auto-generated method stub
		rangodataDAO.update(rangodata);
	}

	@Override
	public void deleteRango(Rangodata rangodata) {
		// TODO Auto-generated method stub
		rangodataDAO.delete(rangodata);
	}

	@Override
	public List<Rangodata> findAllRangodataList() {
		// TODO Auto-generated method stub
		
		return rangodataDAO.find("from Rangodata  order by createTime desc");
	}

	@Override
	public List<Rangodata> findRangodataListByCondition(Rangodata rangodata) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rangodata findRangodataByByCondition(RangodataModel rangodataModel) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from rangodata where 1=1");
		List<Object> paramList = new ArrayList<Object>();
 
		if(!Util.isNull(rangodataModel.getReturnmsg())){
			hql.append(" and  returnmsg = ?");
			paramList.add(rangodataModel.getReturnmsg());
		}
			
		if(!Util.isNull(rangodataModel.getURL())){
			hql.append(" and URL = ?");
			paramList.add(rangodataModel.getURL());
		} 
		if(rangodataModel.getPort()!=null){
			hql.append(" and port = ?");
			paramList.add(rangodataModel.getPort());
		} 
	 
		if (paramList.size() == 0) {
			return null;
		}
		return rangodataDAO.get(hql.toString(), paramList.toArray());
	}

	@Override
	public Rangodata getRangodataById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rangodata findRangodataSByCondition(int port) {
		System.out.println("&&&&&&&&&&&7");
		
		StringBuffer hql = new StringBuffer("from rangodata where pact=? order by id DESC limit 0,1");
		List<Object> paramList = new ArrayList<Object>();		
		RangodataModel rangodataModel=new RangodataModel();
		rangodataModel.setPact("socket");
		paramList.add(rangodataModel.getPact());
		System.out.println(" *************** "+paramList.size());	
		return rangodataDAO.get(hql.toString());
	}

}
