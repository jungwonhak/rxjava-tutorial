package com.lldong0.reactivejava.chapter04.transform;

import static com.lldong0.reactivejava.common.Shape.PUPPLE;
import static com.lldong0.reactivejava.common.Shape.SKY;
import static com.lldong0.reactivejava.common.Shape.YELLOW;
import static com.lldong0.reactivejava.common.Shape.triangle;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class GroupByExample {

	public void marbleDiagram() { 
		String[] objs = {PUPPLE, SKY, triangle(YELLOW), YELLOW, triangle(PUPPLE), triangle(SKY)};
		Observable<GroupedObservable<String, String>> source =
				Observable.fromArray(objs)
				.groupBy(Shape::getShape);
		
		source.subscribe(obj -> {// 각 그룹
			obj.subscribe(val -> // 각 그룹별 데이터
			System.out.println("GROUP:" + obj.getKey() + "\t Value:" + val));
		});
		CommonUtils.exampleComplete();
	}
	
	public void filterBallGroup() { 
		String[] objs = {PUPPLE, SKY, triangle(YELLOW), YELLOW, triangle(PUPPLE), triangle(SKY)};
		Observable<GroupedObservable<String, String>> source =
				Observable.fromArray(objs)
				.groupBy(Shape::getShape);
		
		source.subscribe(obj -> {
			obj.filter(val -> obj.getKey().equals(Shape.BALL))
			.subscribe(val -> 
			System.out.println("GROUP:" + obj.getKey() + "\t Value:" + val));
		});	
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		GroupByExample demo = new GroupByExample();
		demo.marbleDiagram();
		demo.filterBallGroup();
	}
}
