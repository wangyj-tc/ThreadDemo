package com.coop.factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolFactory {
	
	private static ExecutorService executor = Executors.newCachedThreadPool();
	
	public static ExecutorService getCacheExecutor(){
		return executor;
	}
	
	
	

}
