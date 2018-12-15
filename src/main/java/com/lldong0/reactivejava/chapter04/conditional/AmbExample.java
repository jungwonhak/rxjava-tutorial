package com.lldong0.reactivejava.chapter04.conditional;

import static com.lldong0.reactivejava.common.Shape.BLUE;
import static com.lldong0.reactivejava.common.Shape.GREEN;
import static com.lldong0.reactivejava.common.Shape.RED;
import static com.lldong0.reactivejava.common.Shape.SKY;
import static com.lldong0.reactivejava.common.Shape.YELLOW;
import static com.lldong0.reactivejava.common.Shape.rectangle;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmbExample {
	public void marbleDiagram() {
		String[] data1 = {RED, GREEN, BLUE};
		String[] data2 = {rectangle(YELLOW), rectangle(SKY)};
		
		List<Observable<String>> sources = Arrays.asList(
				Observable.fromArray(data1)
						  .doOnComplete(() -> Log.d("Observable #1 : onComplete()")),
				Observable.fromArray(data2)
						  .delay(100L, TimeUnit.MILLISECONDS)
						  .doOnComplete(() -> Log.d("Observable #2 : onComplete()")));
		
		Observable.amb(sources)
				  .doOnComplete(() -> Log.d("Result : onComplete()"))
				  .subscribe(Log::i);		
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		AmbExample demo = new AmbExample();
		demo.marbleDiagram();
	}
}
