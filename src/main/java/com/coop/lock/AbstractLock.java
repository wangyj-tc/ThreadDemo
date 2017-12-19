package com.coop.lock;

import org.I0Itec.zkclient.ZkClient;

/**
 * 抽象类，运用设计模式--模板方式
 * @author yongjian.wang
 *
 */
public abstract class AbstractLock implements Lock {
	
	public static final String  PATH= "/wang";
	
	public ZkClient zk = new ZkClient("10.138.61.18:2181");
	
	public final void getLock(){
		/**
		 * 1.先获取锁--即创建zk的临时节点
		 * 2.获取不到，等待锁
		 * 3.再次尝试获取锁
		 */
		if(tryLock()){
			System.out.println("获取到锁");
		}else{
			waitLock();
			getLock();
			
		}
		
		
	}
	
	public void unLock() {
		if (zk != null) {
			zk.close();
		}
		System.out.println("释放锁");
	}

	abstract void waitLock();

	abstract boolean tryLock();
}
