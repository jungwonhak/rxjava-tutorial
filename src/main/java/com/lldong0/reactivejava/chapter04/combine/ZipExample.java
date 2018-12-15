package com.lldong0.reactivejava.chapter04.combine;

import static com.lldong0.reactivejava.common.Shape.BALL;
import static com.lldong0.reactivejava.common.Shape.PENTAGON;
import static com.lldong0.reactivejava.common.Shape.PUPPLE;
import static com.lldong0.reactivejava.common.Shape.SKY;
import static com.lldong0.reactivejava.common.Shape.STAR;
import static com.lldong0.reactivejava.common.Shape.YELLOW;
import static com.lldong0.reactivejava.common.Shape.triangle;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import com.lldong0.reactivejava.common.Shape;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;


public class ZipExample {

	public void marbleDiagram(){
		String[] shapes = {BALL, PENTAGON, STAR};
		String[] coloredTriangles = {triangle(YELLOW), triangle(PUPPLE), triangle(SKY)};
		
		Observable<String> source = Observable.zip(
			Observable.fromArray(shapes)
					.map(Shape::getSuffix),
			Observable.fromArray(coloredTriangles)
					.map(Shape::getColor),
			(suffix, color) -> color + suffix); 
		
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void zipNumbers() {
		Observable<Integer> source = Observable.zip(
			Observable.just(100, 200, 300),
			Observable.just(10, 20, 30),
			Observable.just(1, 2, 3),
			(a, b, c) -> a + b + c );
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}

	public void zipInterval() { 
		Observable<String> source = Observable.zip(
				Observable.just("RED", "GREEN", "BLUE"),
				Observable.interval(200L, TimeUnit.MILLISECONDS),
				(value,i) -> value);		
		
		CommonUtils.exampleStart();
		source.subscribe(Log::it);			
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();		
	}	
	
	public void zipWithNumbers() {
		Observable<Integer> source = Observable.zip(
			Observable.just(100, 200, 300),
			Observable.just(10, 20, 30),
			(a, b) -> a + b)
			.zipWith(Observable.just(1, 2, 3), (ab, c) -> ab + c);
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ZipExample demo = new ZipExample();
//		demo.marbleDiagram();
//		demo.zipNumbers();
		demo.zipInterval();
//		demo.zipWithNumbers();
	}
}
