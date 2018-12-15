package com.lldong0.reactivejava.chapter04.combine;

import static com.lldong0.reactivejava.common.Shape.DIAMOND;
import static com.lldong0.reactivejava.common.Shape.ORANGE;
import static com.lldong0.reactivejava.common.Shape.PENTAGON;
import static com.lldong0.reactivejava.common.Shape.PUPPLE;
import static com.lldong0.reactivejava.common.Shape.SKY;
import static com.lldong0.reactivejava.common.Shape.STAR;
import static com.lldong0.reactivejava.common.Shape.YELLOW;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import com.lldong0.reactivejava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import java.util.concurrent.TimeUnit;

public class CombineLatestExample {

	public void marbleDiagram() { 
		String[] data1 = {PUPPLE, ORANGE, SKY, YELLOW}; //6, 7, 4, 2
		String[] data2 = {DIAMOND, STAR, PENTAGON};
		
		Observable<String> source = Observable.combineLatest(
				Observable.fromArray(data1)
						  .zipWith( //zipWith()로 깔끔하게 코드 정리
						      Observable.interval(100L, TimeUnit.MILLISECONDS),
							  (shape, notUsed) -> Shape.getColor(shape)),
				Observable.fromArray(data2)
				          .zipWith(
				        	  Observable.interval(150L, 200L, TimeUnit.MILLISECONDS),
				        	  (shape, notUsed) -> Shape.getSuffix(shape)),
				(v1, v2) -> v1 + v2);
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
		
	public void tmp01() {	
		Observable<String> source1 = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(CommonUtils::numberToAlphabet);
		Observable<Long> source2 = Observable.interval(200L, TimeUnit.MILLISECONDS);
		BiFunction<String, Long, String> combiner =
				(val1, val2) -> val1 + val2;
		
		Observable<String> source = Observable.combineLatest(source1, source2, combiner);
		source.subscribe(System.out::println);
		
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) {
		CombineLatestExample demo = new CombineLatestExample();
		demo.marbleDiagram();
	}
}
