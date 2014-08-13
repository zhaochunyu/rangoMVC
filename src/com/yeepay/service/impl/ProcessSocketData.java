package com.yeepay.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.yeepay.entity.Rangodata;
import com.yeepay.model.RangodataModel;
import com.yeepay.service.RangodataService;
public class ProcessSocketData extends Thread {
	private RangodataService rangodataService;
	
	private Socket socket;
	private ServletContext servletContext;
	
	public ProcessSocketData() {
	super();
	}
	public ProcessSocketData(Socket socket, ServletContext servletContext) {
	this.socket = socket;
	this.servletContext = servletContext;
	}
	public void run() {
		Rangodata rangodata = new Rangodata();
		  try {
			   //将输入的字节流转化为高层流
				if (socket != null){					
					//接收Client发来的请求消息
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					// 从流中读取消息内容
					String request = br.readLine(); 
					request=	request.substring(3);
					// 对请求做处理后，生成响应response
					PrintWriter pw = new PrintWriter(socket.getOutputStream());
					RangodataModel rangodataModel;
//					select *  from rangodata where pact="socket" order by id DESC limit 0,1		
					rangodataService = new RangodataServiceImpl();
					rangodata=rangodataService.findRangodataSByCondition(4800);
					System.out.println(rangodata.getReturnmsg());
					pw.println("返回：" + rangodata.getReturnmsg());
//					pw.println("返回：" );
					pw.flush(); // 刷新缓冲区
			
				}
				} catch (IOException e) {
			   e.printStackTrace();
			  }
	}
	}