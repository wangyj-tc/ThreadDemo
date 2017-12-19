package com.coop.demo;
/**
 * synchronized��
 * @author yongjian.wang
 *
 */
public class SynchroizedCustomerService implements Runnable {
	public static CustomerIdGengera customerIdGengera = new CustomerIdGengera();

	public void run() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		
		//ͬ������
		synchronized (SynchroizedCustomerService.class) {
			System.out.println(Thread.currentThread().getName() + " �����û�ID  " + customerIdGengera.initCustId());
		}

	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new SynchroizedCustomerService(), "Thread--" + i).start();
		}
	}

}
