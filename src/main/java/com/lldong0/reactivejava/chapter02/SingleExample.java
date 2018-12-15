package com.lldong0.reactivejava.chapter02;

import com.lldong0.reactivejava.common.Order;
import io.reactivex.Observable;
import io.reactivex.Single;

public class SingleExample {

  public static void main(String[] args) {
    SingleExample demo = new SingleExample();
    demo.just();
    demo.fromObservable();
//		demo.errorCase();
  }

  /**
   * Single Class 오직 1개의 데이터만 발행, 보통 결과가 유일한 서버 API를 호출할 때 유용하게 사용.
   *
   */
  private void just() {
    Single<String> source = Single.just("Hello Single");
    source.subscribe(System.out::println);
  }

  private void fromObservable() {
    //1. 기존 Observable에서 Single 객체로 변환하기
    Observable<String> source = Observable.just("Hello Single");
    Single.fromObservable(source)
        .subscribe(System.out::println);

    //2. single() 메서드를 호출해 Single 객체 생성하기
    Observable.just("Hello Single")
        .single("default item")
        .subscribe(System.out::println);

    //3. first() 메서드를 호출하여 Single 객체 생성하기
    String[] colors = {"Red", "Blue", "Gold"};
    Observable.fromArray(colors)
        .first("default value")
        .subscribe(System.out::println);

    //4. empty Observable에서 Single 객체 생성하기
    Observable.empty()
        .single("default value")
        .subscribe(System.out::println);

    //5. take() 함수에서 Single 객체 생성하기
    Observable.just(new Order("ORD-1"), new Order("ORD-2"))
        .take(1)
        .single(new Order("default order"))
        .subscribe(System.out::println);
  }

  public void errorCase() {
    Single<String> source = Observable.just("Hello Single", "Error").single("default item");
    source.subscribe(System.out::println);
  }

}
