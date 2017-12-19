package com.coop.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生成客户ID
 * @author dell
 *
 */
public class CustomerIdGengera {
	
	//private  int count;
	
	private AtomicInteger count = new AtomicInteger();
	
	
	public String initCustId(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-SS");
		//String result = sdf.format(new Date())+"-"+ ++count;
		String result = sdf.format(new Date())+"-"+ count.incrementAndGet();
		return result;
		
	}

}
