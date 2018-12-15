package com.lldong0.reactivejava.chapter03;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class ReduceExample {

  public static void main(String[] args) {
    ReduceExample demo = new ReduceExample();
    demo.marbleDiagram();
    demo.biFunction();
  }

  private void marbleDiagram() {
    String[] balls = {"1", "3", "5"};
    Maybe<String> source = Observable.fromArray(balls)
        .reduce((ball1, ball2) -> ball2 + "(" + ball1 + ")");
//    source.subscribe(System.out::println);
    source.subscribe(Log::i);
    CommonUtils.exampleComplete();
  }

  private void biFunction() {
    BiFunction<String, String, String> mergeBalls =
        (ball1, ball2) -> ball2 + "(" + ball1 + ")";

    String[] balls = {"1", "3", "5"};
    Maybe<String> source = Observable.fromArray(balls)
        .reduce(mergeBalls);
    source.subscribe(System.out::println);
    CommonUtils.exampleComplete();
  }

}
