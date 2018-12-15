package com.lldong0.reactivejava.chapter02.hotObservable.subject;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

public class AsyncSubjectExample {

  public static void main(String[] args) {
    AsyncSubjectExample demo = new AsyncSubjectExample();
    demo.marbleDiagram();
    System.out.println("------");
    demo.asSubscriber();
    System.out.println("------");
    demo.coldObservable();
  }

  /**
   * AsyncSubject 를 이용해서 차가운 Observable 에서 뜨거운 Observable 로 변경하는 방법
   */
  private void marbleDiagram() {
    AsyncSubject<Integer> subject = AsyncSubject.create();
    // 첫번째 구독자
    subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
    subject.onNext(1);
    subject.onNext(2);
    // 두번째 구독자
    subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
    subject.onNext(3);
    subject.onComplete();
    subject.onNext(13);
    subject.subscribe(data -> System.out.println("Subscriber #3 => " + data));
  }

  private void asSubscriber() {
    Float[] temperature = {10.1f, 13.4f, 12.5f};
    Observable<Float> source = Observable.fromArray(temperature);

    AsyncSubject<Float> subject = AsyncSubject.create();
    subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));

    source.subscribe(subject);
  }

  private void coldObservable() {
    Float[] temp = {10.1f, 13.4f, 12.5f};
    Observable<Float> source = Observable.fromArray(temp);
    source.subscribe(data -> System.out.println("Cold Observable " + data));
  }
}
