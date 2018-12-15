package com.lldong0.reactivejava.chapter05.schedulers;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorSchedulerExample {
	public void run() { 
		final int THREAD_NUM = 10;
		
		String[] data = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(data);
		Executor executor = Executors.newFixedThreadPool(THREAD_NUM);
		
		source.subscribeOn(Schedulers.from(executor))
				.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
				.subscribe(Log::i);
		CommonUtils.sleep(500);		
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ExecutorSchedulerExample demo = new ExecutorSchedulerExample();
		demo.run();
	}
}
