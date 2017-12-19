package com.coop.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 最安全的单例
 * @author yongjian.wang
 *
 */
public class SingleCustomerIdGengera {
	
	private static SingleCustomerIdGengera customerIdGengera = new SingleCustomerIdGengera();
	
	private AtomicInteger count = new AtomicInteger();
	
	
	private SingleCustomerIdGengera(){}
	
	public static SingleCustomerIdGengera getSingleInstance(){
		return customerIdGengera;
	}
	
	public String initCustId(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-SS");
		//String result = sdf.format(new Date())+"-"+ ++count;
		String result = sdf.format(new Date())+"-"+ count.incrementAndGet();
		return result;
		
	}

}
