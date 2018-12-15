package com.lldong0.reactivejava.chapter02;

import io.reactivex.Observable;
import java.util.concurrent.Callable;

public class ObservableFromCallable {

  public static void main(String[] args) {
    ObservableFromCallable demo = new ObservableFromCallable();
    demo.basic();
    demo.withoutLambda();
  }

  public void basic() {
    Callable<String> callable = () -> {
      Thread.sleep(1000);
      return "Hello Callable";
    };
    Observable<String> source = Observable.fromCallable(callable);
    source.subscribe(System.out::println);
  }

  public void withoutLambda() {
    Callable<String> callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        Thread.sleep(1000);
        return "Hello Callable without Lambda";
      }
    };
    Observable<String> source = Observable.fromCallable(callable);
    source.subscribe(System.out::println);

  }

}
