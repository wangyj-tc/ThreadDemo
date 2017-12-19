package com.coop.lock;

import org.I0Itec.zkclient.ZkClient;

/**
 * �����࣬�������ģʽ--ģ�巽ʽ
 * @author yongjian.wang
 *
 */
public abstract class AbstractLock implements Lock {
	
	public static final String  PATH= "/wang";
	
	public ZkClient zk = new ZkClient("10.138.61.18:2181");
	
	public final void getLock(){
		/**
		 * 1.�Ȼ�ȡ��--������zk����ʱ�ڵ�
		 * 2.��ȡ�������ȴ���
		 * 3.�ٴγ��Ի�ȡ��
		 */
		if(tryLock()){
			System.out.println("��ȡ����");
		}else{
			waitLock();
			getLock();
			
		}
		
		
	}
	
	public void unLock() {
		if (zk != null) {
			zk.close();
		}
		System.out.println("�ͷ���");
	}

	abstract void waitLock();

	abstract boolean tryLock();
}
