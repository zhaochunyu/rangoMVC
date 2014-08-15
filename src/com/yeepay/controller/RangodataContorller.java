package com.yeepay.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeepay.entity.Rangodata;
import com.yeepay.model.RangodataModel;
import com.yeepay.service.RangodataService;
import com.yeepay.service.UserService;
import com.yeepay.service.impl.RangodataServiceImpl;
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
		BeanUtils.copyProperties(rangodataModel, rangodata); //实体属性复制，将rangodataModel中的属性值复制到rangodata中
		rangodata.setCreateTime(date);
		rangodata.setUpdateTime(date);
		rangodataService.saveRango(rangodata);
//		return "redirect:/user/index";
		return "result";
	}
	
	@RequestMapping("/getSocket")
	public void getSocket(RangodataModel rangodataModel) {
		Rangodata rangodata = new Rangodata();
		BeanUtils.copyProperties(rangodataModel, rangodata); 	
	rangodata=rangodataService.findRangodataSByCondition(4800);
	System.out.println(rangodata.getReturnmsg());
	}
	
	@RequestMapping("/reSocket")
	public void reSocket() {
		// TODO Auto-generated method stub
		Socket socket = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		String host="127.0.0.1";
		Integer port =4800;
		String name="chun";
		String	requestMsg=name;
		try {
			//利用Server的IP和端口创建socket对象
		
			socket = new Socket(host, port);
			
			//创建writer对象，并向Server发送请求
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8")));
			pw.println(requestMsg);
			pw.flush();
			
			//接收Server相应信息，并做进一步处理
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("OK--> "+ br.readLine());
			
		} 
		catch (UnknownHostException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("The Server : "+host+" is Unknown !");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
