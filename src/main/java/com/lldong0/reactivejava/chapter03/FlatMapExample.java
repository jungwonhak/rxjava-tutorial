package com.lldong0.reactivejava.chapter03;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class FlatMapExample {

  public static void main(String[] args) {
    FlatMapExample demo = new FlatMapExample();
    demo.marbleDiagram();
    demo.flatMapLambda();
  }

  private void marbleDiagram() {
    //함수를 별도로 정의하는 것이 가장 먼저 겪어야 할 관문임
    Function<String, Observable<String>> getDoubleDiamonds =
        ball -> Observable.just(ball + "<>", ball + "<>");

    String[] balls = {"1", "2", "3"};
    Observable<String> source = Observable.fromArray(balls)
        .flatMap(getDoubleDiamonds);
    source.subscribe(Log::i);
    CommonUtils.exampleComplete();
  }

  private void flatMapLambda() {
    String[] balls = {"1", "2", "3"};
    Observable<String> source = Observable.fromArray(balls)
        .flatMap(ball -> Observable.just(ball + "<>", ball + "<>"));
    source.subscribe(Log::i);
    CommonUtils.exampleComplete();
  }
}
