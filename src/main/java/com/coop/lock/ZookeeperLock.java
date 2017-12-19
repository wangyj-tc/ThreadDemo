package com.coop.lock;

import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.IZkDataListener;

public class ZookeeperLock extends AbstractLock {

	public CountDownLatch countDownLatch;

	@Override
	public void waitLock() {
		IZkDataListener izkDataListener = new IZkDataListener() {
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("�յ��ͷ�����֪ͨ");
				if (countDownLatch != null) {
					countDownLatch.countDown();
				}
			}

			public void handleDataChange(String dataPath, Object data) throws Exception {

			}
		};
		//ע���¼�
		zk.subscribeDataChanges(PATH, izkDataListener);
		if (zk.exists(PATH)) {
			try {
				countDownLatch = new CountDownLatch(1);
				countDownLatch.await();
			} catch (InterruptedException e) {
				
			}
		}
		zk.unsubscribeDataChanges(PATH, izkDataListener);
	}

	@Override
	public boolean tryLock() {
		/**
		 * ������ʱ�ڵ�
		 */
		try {
			zk.createEphemeral(PATH);
			return true;
		} catch (Exception e) {
			System.out.println("��ʱ�ڵ㴴��ʧ��...�ȴ���");
			return false;
		}
		
	}

}
