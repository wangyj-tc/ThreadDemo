package com.coop.demo;

/**
 * ��ǰ��������һ����������ʱ����Ӧ��ʹ��volatile������ 
 * 1.�Ա�����д����������������ĵ�ǰֵ����������ȷ��ֻ�е����̸߳��±�����ֵ��
 * 2.�ñ�������������״̬����һ�����벻���������С� 
 * 3.�ڷ��ʱ���ʱ����Ҫ����
 * 
 * @author yongjian.wang
 *
 */
public class VolatileDemo {

	public static  boolean flag;

	public static void main(String[] args) {

		Thread thread = new Thread(new Runnable() {

			public void run() {
				int count = 0;
				while (!flag) {
					count++;
				}

			}
		}, "Thread-work_1");
		thread.start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		flag = true;

	}

}
