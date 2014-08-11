package com.yeepay.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yeepay.dao.BaseDAO;
import com.yeepay.entity.User;
import com.yeepay.model.UserModel;
import com.yeepay.service.UserService;
import com.yeepay.util.Util;
import com.yeepay.util.page.Page;
import com.yeepay.util.page.PageResultSet;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private BaseDAO<User> userDao;
	
	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public List<User> findAllUserList() {

		return userDao.find("from User u order by u.createTime desc");
	}

	@Override
	public List<User> findUserListByCondition(UserModel userModel) {
		StringBuffer hql = new StringBuffer("from User u where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(!Util.isNull(userModel.getUsername())){
			hql.append(" and u.username = ?");
			paramList.add(userModel.getUsername());
		}
			
		if(!Util.isNull(userModel.getPassword())){
			hql.append(" and u.password = ?");
			paramList.add(userModel.getPassword());
		} 
		if(!Util.isNull(userModel.getEmail())){
			hql.append(" and u.email = ?");
			paramList.add(userModel.getEmail());
		} 
		
		if(!Util.isNull(userModel.getUpdateUser())) {
			hql.append(" and u.updateUser = ?");
			paramList.add(userModel.getUpdateUser());
		}
		if(!Util.isNull(userModel.getCreateUser())){
			hql.append(" and u.createUser = ?");
			paramList.add(userModel.getCreateUser());
		} 
		if (userModel.getSex() != null) {
			hql.append(" and u.sex = ?");
			paramList.add(userModel.getSex());
		}
		return userDao.find(hql.toString(), paramList);
	}

	@Override
	public User findUserByByCondition(UserModel userModel) {
		StringBuffer hql = new StringBuffer("from User u where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(!Util.isNull(userModel.getUsername())){
			hql.append(" and u.username = ?");
			paramList.add(userModel.getUsername());
		}
			
		if(!Util.isNull(userModel.getPassword())){
			hql.append(" and u.password = ?");
			paramList.add(userModel.getPassword());
		} 
		if(!Util.isNull(userModel.getEmail())){
			hql.append(" and u.email = ?");
			paramList.add(userModel.getEmail());
		} 
		
		if(!Util.isNull(userModel.getUpdateUser())) {
			hql.append(" and u.updateUser = ?");
			paramList.add(userModel.getUpdateUser());
		}
		if(!Util.isNull(userModel.getCreateUser())){
			hql.append(" and u.createUser = ?");
			paramList.add(userModel.getCreateUser());
		} 
		if (userModel.getSex() != null) {
			hql.append(" and u.sex = ?");
			paramList.add(userModel.getSex());
		}
		if (paramList.size() == 0) {
			return null;
		}
		return userDao.get(hql.toString(), paramList.toArray());
	}

	@Override
	public User getUserById(int id) {
		return userDao.get(User.class, id);
	}

	@Override
	public PageResultSet<User> findPageUserList(UserModel userModel, int page,
			int pageSize) {
		
		StringBuffer hql = new StringBuffer("from User u where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(!Util.isNull(userModel.getUsername())){
			hql.append(" and u.username = ?");
			paramList.add(userModel.getUsername());
		}
			
		if(!Util.isNull(userModel.getPassword())){
			hql.append(" and u.password = ?");
			paramList.add(userModel.getPassword());
		} 
		if(!Util.isNull(userModel.getEmail())){
			hql.append(" and u.email = ?");
			paramList.add(userModel.getEmail());
		} 
		
		if(!Util.isNull(userModel.getUpdateUser())) {
			hql.append(" and u.updateUser = ?");
			paramList.add(userModel.getUpdateUser());
		}
		if(!Util.isNull(userModel.getCreateUser())){
			hql.append(" and u.createUser = ?");
			paramList.add(userModel.getCreateUser());
		} 
		if (userModel.getSex() != null) {
			hql.append(" and u.sex = ?");
			paramList.add(userModel.getSex());
		}
		hql.append(" order by u.createTime desc");
		
		Long totalRow = userDao.count(hql.toString(), paramList); 
		
		Page pages = new Page(totalRow.intValue(), pageSize, page);
		//这里用到了Page类中的获取首页和分页大小的方法
		List<User> list = userDao.find(hql.toString(), paramList, page, pageSize);

		//把取出来的结果放到list中
		PageResultSet<User> pageResultSet = new PageResultSet<User>();

		pageResultSet.setList(list);

		pageResultSet.setPage(pages);

		return pageResultSet;
	}

}
