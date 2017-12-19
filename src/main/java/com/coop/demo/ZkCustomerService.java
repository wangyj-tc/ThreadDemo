package com.coop.demo;

import com.coop.lock.Lock;
import com.coop.lock.ZookeeperLock;
/**
 * 分布式锁
 * @author yongjian.wang
 *
 */
public class ZkCustomerService implements Runnable {

	public static CustomerIdGengera customerIdGengera = new CustomerIdGengera();

	private Lock lock = new ZookeeperLock();

	public void run() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		lock.getLock();
		System.out.println(Thread.currentThread().getName() + " 生成用户ID  " + customerIdGengera.initCustId());
		lock.unLock();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new ZkCustomerService(), "Thread--" + i).start();
		}
	}

}
