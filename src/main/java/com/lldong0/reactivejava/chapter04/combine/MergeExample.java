package com.lldong0.reactivejava.chapter04.combine;

import static com.lldong0.reactivejava.common.Shape.GREEN;
import static com.lldong0.reactivejava.common.Shape.PUPPLE;
import static com.lldong0.reactivejava.common.Shape.RED;
import static com.lldong0.reactivejava.common.Shape.SKY;
import static com.lldong0.reactivejava.common.Shape.YELLOW;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class MergeExample {

	public void marbleDiagram() { 
		String[] data1 = {RED, GREEN}; //1, 3
		String[] data2 = {YELLOW, SKY, PUPPLE}; //2, 4, 6
		
		Observable<String> source1 = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> data1[idx])
				.take(data1.length);
		Observable<String> source2 = Observable.interval(50L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> data2[idx])
				.take(data2.length);

		Observable<String> source = Observable.merge(source1, source2);
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		MergeExample demo = new MergeExample();
		demo.marbleDiagram();
	}
}
