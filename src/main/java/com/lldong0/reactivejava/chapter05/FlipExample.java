package com.lldong0.reactivejava.chapter05;

import static com.lldong0.reactivejava.common.Shape.GREEN;
import static com.lldong0.reactivejava.common.Shape.RED;
import static com.lldong0.reactivejava.common.Shape.YELLOW;
import static com.lldong0.reactivejava.common.Shape.pentagon;
import static com.lldong0.reactivejava.common.Shape.star;
import static com.lldong0.reactivejava.common.Shape.triangle;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import com.lldong0.reactivejava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class FlipExample {

  public void marbleDiagram() {
    String[] objs = {star(RED), triangle(YELLOW), pentagon(GREEN)};
    Observable<String> source = Observable.fromArray(objs)
        .doOnNext(data -> Log.v("Original data = " + data))
        .subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.newThread())
        .map(Shape::flip); // 도형 뒤집기 함수
    source.subscribe(Log::i);
    CommonUtils.sleep(500);
    CommonUtils.exampleComplete();
  }

  public void observeOnRemoved() {
    String[] objs = {star(RED), triangle(YELLOW), pentagon(GREEN)};
    Observable<String> source = Observable.fromArray(objs)
        .doOnNext(data -> Log.v("Origianl data = " + data))
        .subscribeOn(Schedulers.newThread())
//        .observeOn(Schedulers.newThread())
        .map(Shape::flip);
    source.subscribe(Log::i);
    CommonUtils.sleep(500);
  }

  public static void main(String[] args) {
    FlipExample demo = new FlipExample();
    demo.marbleDiagram();
    demo.observeOnRemoved();
  }
}
