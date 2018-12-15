package com.lldong0.reactivejava.chapter04.transform;

import static com.lldong0.reactivejava.common.Shape.BLUE;
import static com.lldong0.reactivejava.common.Shape.GREEN;
import static com.lldong0.reactivejava.common.Shape.RED;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class SwitchMapExample {

  public void marbleDiagram() {
    CommonUtils.exampleStart(); //시간을 측정하기 위해 호출

    String[] balls = {RED, GREEN, BLUE};
    Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
        .map(Long::intValue)
        .map(idx -> balls[idx])
        .take(balls.length)
        .switchMap(
            ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                .map(notUsed -> ball + "<>")
                .take(2));
    source.subscribe(Log::it);
    CommonUtils.sleep(2000);
    CommonUtils.exampleComplete();
  }

  public void usingDoOnNext() {
    CommonUtils.exampleStart(); //시간을 측정하기 위해 호출

    String[] balls = {RED, GREEN, BLUE};
    Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
        .map(Long::intValue)
        .map(idx -> balls[idx])
        .take(balls.length)
        .doOnNext(Log::dt)  //중간결과 확인용
        .switchMap(
            ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                .map(noValue -> ball + "<>")
                .take(2));
    source.subscribe(Log::it);
    CommonUtils.sleep(2000);
    CommonUtils.exampleComplete();
  }

  public static void main(String[] args) {
    SwitchMapExample demo = new SwitchMapExample();
    demo.marbleDiagram();
    demo.usingDoOnNext();
  }
}
