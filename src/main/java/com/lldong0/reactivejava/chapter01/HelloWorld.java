package com.lldong0.reactivejava.chapter01;

import io.reactivex.Observable;// 기본 패키지 명

public class HelloWorld {

  private void emit() {
    // 데이터 변화가 발생하는 데이터 소
    Observable
        .just("Hello", "RxJava 2!!")
        .subscribe(System.out::println);

  }

  public static void main(String[] args) {
    HelloWorld helloWorld = new HelloWorld();
    helloWorld.emit();

    System.out.println("------");

    Observable
        .just("Hello", "RxJava 2!!")
        .subscribe(System.out::println);

  }

}
