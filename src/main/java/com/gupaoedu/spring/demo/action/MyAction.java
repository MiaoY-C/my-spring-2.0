package com.gupaoedu.spring.demo.action;

import com.gupaoedu.spring.demo.service.IModifyService;
import com.gupaoedu.spring.demo.service.IQueryService;
import com.gupaoedu.spring.formework.annotation.GPAutowired;
import com.gupaoedu.spring.formework.annotation.GPController;
import com.gupaoedu.spring.formework.annotation.GPRequestMapping;
import com.gupaoedu.spring.formework.annotation.GPRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 公布接口url
 * @author Tom
 *
 */
@GPController
@GPRequestMapping("/web")
public class MyAction {

	@GPAutowired IQueryService queryService;
	@GPAutowired IModifyService modifyService;

	@GPRequestMapping("/query.json")
	public String query(HttpServletRequest request, HttpServletResponse response,
								@GPRequestParam("name") String name){
		String result = queryService.query(name);
		return out(response,result);
	}
	
	@GPRequestMapping("/add*.json")
	public String add(HttpServletRequest request,HttpServletResponse response,
			   @GPRequestParam("name") String name,@GPRequestParam("addr") String addr){
		String result = null;
		try {
			result = modifyService.add(name,addr);
			return out(response,result);
		} catch (Exception e) {
//			e.printStackTrace();
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("detail",e.getCause().getMessage());
//			System.out.println(Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]",""));
			model.put("stackTrace", Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]",""));
			return "500";
		}

	}
	
	@GPRequestMapping("/remove.json")
	public String remove(HttpServletRequest request,HttpServletResponse response,
		   @GPRequestParam("id") Integer id){
		String result = modifyService.remove(id);
		return out(response,result);
	}
	
	@GPRequestMapping("/edit.json")
	public String edit(HttpServletRequest request,HttpServletResponse response,
			@GPRequestParam("id") Integer id,
			@GPRequestParam("name") String name){
		String result = modifyService.edit(id,name);
		return out(response,result);
	}
	
	
	
	private String out(HttpServletResponse resp,String str){
		try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
