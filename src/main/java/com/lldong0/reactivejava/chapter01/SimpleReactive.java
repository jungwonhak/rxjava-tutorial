package com.lldong0.reactivejava.chapter01;

import io.reactivex.Observable;

public class SimpleReactive {

  private void run() {
    Observable<Integer> observable1 = Observable.just(1,2);
    Observable<Integer> observable2 = Observable.just(3,4);
    Observable.combineLatest(observable1, observable2, (x, y) -> x + y)
        .subscribe(System.out::println);

  }

  public static void main(String[] args) {
    new SimpleReactive().run();
  }
}
