package com.lldong0.reactivejava.chapter02;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Consumer;

public class ObservableCreateExample {

  public static void main(String[] args) {
    ObservableCreateExample test = new ObservableCreateExample();
    test.basic();
  }

  public void basic() {
    Observable<Integer> source = Observable.create(
        (ObservableEmitter<Integer> emitter) -> {
          emitter.onNext(100);
          emitter.onNext(200);
          emitter.onNext(300);
          emitter.onComplete();
        }
    );
    // 메서드 참조
    source.subscribe(System.out::println);
    // 람다
    source.subscribe(data -> System.out.println("Result : " + data));
    // 익명 클래스
    source.subscribe(new Consumer<Integer>() {
      @Override
      public void accept(Integer data) throws Exception {
        System.out.println("Result : " + data);
      }
    });
  }


}
