package com.lldong0.reactivejava.chapter04.create;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class IntervalExample {
	public void printNumbers() { 
		CommonUtils.exampleStart();
		Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
//				.map(data -> (data + 1) * 100)
				.take(5);
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public void noInitialDelay() { 
		CommonUtils.exampleStart();
		Observable<Long> source = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
				.map(val -> val + 100)
				.take(5);
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();		
	}
	
	public static void main(String[] args) { 
		IntervalExample demo = new IntervalExample();
		demo.printNumbers();
//		demo.noInitialDelay();
	}
}
