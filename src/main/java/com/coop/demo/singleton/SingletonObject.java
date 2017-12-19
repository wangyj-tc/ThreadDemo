package com.coop.demo.singleton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.coop.factory.ThreadPoolFactory;

/**
 * 单例测试类，懒汉式
 * 
 * @author yongjian.wang
 *
 */
public class SingletonObject {

	private static SingletonObject instance;

	private SingletonObject() {
	}

	public static SingletonObject getInstance() {
		synchronized (SingletonObject.class) {
			if (instance == null) {
				instance = new SingletonObject();
			}
		}

		return instance;
	}

	public static void main(String[] args) throws InterruptedException {
		int threadCounts = 100;
		int testCounts = 100000;
		for (int i = 0; i < testCounts; i++) {
			test(threadCounts);
		}
	}

	public static void test(int threadCounts) throws InterruptedException {
		ExecutorService executorService = ThreadPoolFactory.getCacheExecutor();
		final CountDownLatch startFlag = new CountDownLatch(1);
		final CountDownLatch counter = new CountDownLatch(threadCounts);
		final Set<String> instanceSet = Collections.synchronizedSet(new HashSet<String>());
		for (int i = 0; i < threadCounts; i++) {
			executorService.execute(new Runnable() {
				public void run() {
					try {
						startFlag.await();
					} catch (InterruptedException e) {
					}
					instanceSet.add(SingletonObject.getInstance().toString());
					counter.countDown();
				}
			});
		}

		// 等待所有的线程，然后一起跑(发令枪模式)
		startFlag.countDown();

		// 等待所有的线程跑完，继续往下执行（类似于join模式）
		counter.await();
		SingletonObject.instance = null;
		if (instanceSet.size() > 1) {
			System.out.print("{");
			for (String instance : instanceSet) {
				System.out.print("[" + instance + "]");
			}
			System.out.println("}");
		}
	}

}
