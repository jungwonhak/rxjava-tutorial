package com.lldong0.reactivejava.chapter02;

import io.reactivex.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ObservableFromFuture {

  public static void main(String[] args) {
    ObservableFromFuture demo = new ObservableFromFuture();
    demo.basic();
    demo.withoutLambda();
  }

  /**
   * 동시성 API로 비동기 계산의 결과를 구할 때 사용
   */
  private void basic() {
    Future<String> future = Executors.newSingleThreadExecutor()
        .submit(() -> {
          Thread.sleep(1000);
          return "Hello Future";
        });
    Observable<String> source = Observable.fromFuture(future);
    source.subscribe(System.out::println);
  }

  private void withoutLambda() {
    Callable<String> callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        Thread.sleep(1000);
        return "Hello Future(No Lambda)";
      }
    };

    Future<String> future = Executors.newSingleThreadExecutor()
        .submit(callable);
    Observable<String> source = Observable.fromFuture(future);
    source.subscribe(System.out::println);
  }
}
