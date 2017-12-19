package com.coop.demo;

import org.springframework.boot.lang.UsesUnsafeJava;

/**
 * 
 * @author yongjian.wang
 *
 */
@UsesUnsafeJava
public class CustomerService implements Runnable {

	//public static CustomerIdGengera customerIdGengera = new CustomerIdGengera();
	
	public SingleCustomerIdGengera customerIdGengera = SingleCustomerIdGengera.getSingleInstance();

	public void run() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		System.out.println(Thread.currentThread().getName() + " 生成用户ID  " + customerIdGengera.initCustId());
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new CustomerService(), "Thread--" + i).start();
		}
	}

}
