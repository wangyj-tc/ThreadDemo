package com.coop.demo.notify;

import java.util.concurrent.Exchanger;
/**
 * 用于线程之间交换数据，多数据库用于下载提高速度
 * @author yongjian.wang
 *
 */
public class ExchangerDemo {

	public static void main(String[] args) throws InterruptedException {
		final Exchanger<String> exchanger = new Exchanger<String>();
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("线程1等待接受");
					String content = exchanger.exchange("thread1");
					System.out.println("线程1收到的为：" + content);
				} catch (InterruptedException e) {
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("线程2等待接受并沉睡3秒");
					Thread.sleep(3000);
					String content = exchanger.exchange("thread2");
					System.out.println("线程2收到的为：" + content);
				} catch (InterruptedException e) {
				}
			}
		});
		thread1.start();
		thread2.start();
	}

}
