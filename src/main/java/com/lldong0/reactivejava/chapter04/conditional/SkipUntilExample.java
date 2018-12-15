package com.lldong0.reactivejava.chapter04.conditional;

import static com.lldong0.reactivejava.common.Shape.BLUE;
import static com.lldong0.reactivejava.common.Shape.GREEN;
import static com.lldong0.reactivejava.common.Shape.PUPPLE;
import static com.lldong0.reactivejava.common.Shape.RED;
import static com.lldong0.reactivejava.common.Shape.SKY;
import static com.lldong0.reactivejava.common.Shape.YELLOW;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class SkipUntilExample {
	public void marbleDiagram() {
		String[] data = {RED, YELLOW, GREEN, SKY, BLUE, PUPPLE};
		
		Observable<String> source = Observable.fromArray(data)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
						(val, notUsed) -> val)
				.skipUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		SkipUntilExample demo = new SkipUntilExample();
		demo.marbleDiagram();
	}
}
