package com.lldong0.reactivejava.chapter02.hotObservable.subject;

import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectExample {

  public static void main(String[] args) {
    ReplaySubjectExample demo = new ReplaySubjectExample();
    demo.marbleDiagram();
  }

  /**
   * ReplaySubject 는 데이터를 처음부터 마지막까지 전부 반환
   */
  public void marbleDiagram() {
    ReplaySubject<String> subject = ReplaySubject.create();
    subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
    subject.onNext("1");
    subject.onNext("2");
    subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
    subject.onNext("3");
    subject.onComplete();
  }

}
