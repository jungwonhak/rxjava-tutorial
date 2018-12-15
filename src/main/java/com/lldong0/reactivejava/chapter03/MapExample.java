package com.lldong0.reactivejava.chapter03;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MapExample {

  public static void main(String[] args) {
    MapExample demo = new MapExample();
    demo.marbleDiagram();
    demo.mapFunction();
    demo.mappingType();
  }

  private void marbleDiagram() {
    String[] balls = {"1", "2", "3", "4"};
    Observable<String> source = Observable.fromArray(balls)
        .map(ball -> ball + "<>");
    source.subscribe(Log::i);
    CommonUtils.exampleComplete();
  }

  private void mapFunction() {
    // 제네릭 함수형 인터페에스
    Function<String, String> getDiamond = ball -> ball + "<>";

    String[] balls = {"1", "2", "3", "4"};
    Observable<String> source = Observable.fromArray(balls)
        .map(getDiamond);
    source.subscribe(Log::i);
    CommonUtils.exampleComplete();
  }

  private void mappingType() {
    Function<String, Integer> getIndex = ball -> {
      switch (ball) {
        case "RED":
          return 1;
        case "YELLOW":
          return 2;
        case "GREEN":
          return 3;
        case "BLUE":
          return 4;
        default:
          return -1;
      }
    };

    String[] balls = {"RED", "YELLOW", "GREEN", "BLUE"};
    Observable<Integer> source = Observable.fromArray(balls)
        .map(getIndex); //명시적인 타입 변환없이 바로 사용할 수 있음
    source.subscribe(Log::i);
    CommonUtils.exampleComplete();
  }

}
