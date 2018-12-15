package com.lldong0.reactivejava.chapter02.hotObservable.subject;

import io.reactivex.subjects.BehaviorSubject;

public class BehaviorSubjectExample {

  public static void main(String[] args) {
    BehaviorSubjectExample demo = new BehaviorSubjectExample();
    demo.marbleDiagram();
  }

  /**
   * BehaviorSubject 는 구독자에게 가장 최신값 또는 기본값을 반환
   */
  private void marbleDiagram() {
    BehaviorSubject<String> subject = BehaviorSubject.createDefault("6");
    subject.subscribe(data -> System.out.println("Subscriber #1 =>" + data));
    subject.onNext("1");
    subject.onNext("3");
    subject.subscribe(data -> System.out.println("Subscriber #2 =>" + data));
    subject.onNext("5");
    subject.onComplete();

  }
}
