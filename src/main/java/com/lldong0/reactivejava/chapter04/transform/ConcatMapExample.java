package com.lldong0.reactivejava.chapter04.transform;

import static com.lldong0.reactivejava.common.Shape.BLUE;
import static com.lldong0.reactivejava.common.Shape.GREEN;
import static com.lldong0.reactivejava.common.Shape.RED;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class ConcatMapExample  {
	public void marbleDiagram() { 
		CommonUtils.exampleStart(); //시간을 측정하기 위해 호출
		
		String[] balls = {RED, GREEN, BLUE}; //1, 3, 5
		Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> balls[idx])
				.take(balls.length)
				.concatMap(
					ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
									.map(notUsed -> ball + "<>")
									.take(2)); //2개의 다이아몬드
		source.subscribe(Log::it);
		CommonUtils.sleep(2000);
		CommonUtils.exampleComplete();
	}
	
	public void interleaving() { 
		CommonUtils.exampleStart(); //시간을 측정하기 위해 호출
		
		String[] balls = {RED, GREEN, BLUE};
		Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> balls[idx])
				.take(3)
				.flatMap(
					ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
									.map(notUsed -> ball + "<>")
									.take(2));
		source.subscribe(Log::it);
		CommonUtils.sleep(2000);
		CommonUtils.exampleComplete();
	}
	
	
	public static void main(String[] args) { 
		ConcatMapExample demo = new ConcatMapExample();
		demo.marbleDiagram();
		demo.interleaving();
	}
}
