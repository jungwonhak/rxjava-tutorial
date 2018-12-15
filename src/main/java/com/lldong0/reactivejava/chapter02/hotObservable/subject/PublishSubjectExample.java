package com.lldong0.reactivejava.chapter02.hotObservable.subject;

import io.reactivex.subjects.PublishSubject;

public class PublishSubjectExample {

  public static void main(String[] args) {
    PublishSubjectExample demo = new PublishSubjectExample();
    demo.marbleDiagram();
  }

  private void marbleDiagram() {
    PublishSubject<Integer> subject = PublishSubject.create();
    subject.subscribe(data -> System.out.println("1번 구독자 : " + data));
    subject.onNext(1);
    subject.onNext(2);
    subject.onNext(3);
    subject.subscribe(data -> System.out.println("2번 구독자 : " + data));
    subject.onNext(4);
    subject.onComplete();
  }
}
