package com.yeepay.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeepay.entity.Rangodata;
import com.yeepay.model.RangodataModel;
import com.yeepay.service.RangodataService;
import com.yeepay.service.UserService;
import com.yeepay.util.Constants;
import com.yeepay.util.Util;
@Controller
//存储rango数据
public class RangodataContorller {
	@Resource
	private RangodataService rangodataService;
	@RequestMapping("/rangodata")
	public String addRango(RangodataModel rangodataModel){
		Date date = new Date();
		Rangodata rangodata = new Rangodata();
		BeanUtils.copyProperties(rangodataModel, rangodata); //实体属性复制，将userModel中的属性值复制到User中
//		rangodata.setCreateTime(date);	 
		System.out.println(rangodata.getReturnmsg());
		System.out.println(rangodata);
		System.out.println(rangodata.getURL());
		System.out.println(rangodata.getPort());
//		System.out.println(rangodata.getCreateTime());
		rangodataService.saveRango(rangodata);
		return "redirect:/user/index";
	}

}
