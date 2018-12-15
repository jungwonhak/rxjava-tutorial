package com.lldong0.reactivejava.chapter04.transform;

import static com.lldong0.reactivejava.common.Shape.BLUE;
import static com.lldong0.reactivejava.common.Shape.GREEN;
import static com.lldong0.reactivejava.common.Shape.RED;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;

public class ScanExample  {
	public void marbleDiagram() { 
		String[] balls = {RED, GREEN, BLUE}; //1,3,5
		Observable<String> source = Observable.fromArray(balls)
				.scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ScanExample demo = new ScanExample();
		demo.marbleDiagram();
	}
}
