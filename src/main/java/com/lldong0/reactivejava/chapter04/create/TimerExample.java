package com.lldong0.reactivejava.chapter04.create;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimerExample {
	public void showTime() {
		CommonUtils.exampleStart();
		Observable<String> source = Observable.timer(500L, TimeUnit.MILLISECONDS)
				.map(notUsed -> { 
					return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
							.format(new Date());
				});
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		TimerExample demo = new TimerExample(); 
		demo.showTime();
	}
}
