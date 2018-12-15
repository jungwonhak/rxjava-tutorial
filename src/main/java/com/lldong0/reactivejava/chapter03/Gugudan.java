package com.lldong0.reactivejava.chapter03;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.Scanner;

public class Gugudan {

  public static void main(String[] args) {
    Gugudan demo = new Gugudan();
//    demo.plainJava();
//    demo.reactiveV1();
    demo.usingResultSelector();
  }

  private void plainJava() {
    Scanner in = new Scanner(System.in);
    System.out.println("Gugudan Input : ");
    int dan = in.nextInt();
    for (int row = 1; row < 10; row++) {
      System.out.println(dan + " * " + row + " = " + dan * row);
    }
    in.close();
  }

  private void reactiveV1() {
    Scanner in = new Scanner(System.in);
    System.out.println("Gugudan Input:");
    int dan = in.nextInt();

    Observable<Integer> source = Observable.range(1, 9);
    source.subscribe(row -> System.out.println(dan + " * " + row + " = " + dan * row));
    in.close();
  }

  private void reactiveV2() {
    Scanner in = new Scanner(System.in);
    System.out.println("Gugudan Input:");
    int dan = in.nextInt();

    Function<Integer, Observable<String>> gugudan = num ->
        Observable.range(1, 9)
            .map(row -> num + " * " + row + " = " + num * row);
    Observable<String> source = Observable.just(dan).flatMap(gugudan);
    source.subscribe(System.out::println);
    in.close();
  }

  private void reactiveV3() {
    Scanner in = new Scanner(System.in);
    System.out.println("Gugudan Input:");
    int dan = in.nextInt();

    Observable<String> source = Observable.just(dan)
        .flatMap(num -> Observable.range(1, 9)
            .map(row -> num + " * " + row + " = " + dan * row));
    source.subscribe(System.out::println);
    in.close();
  }

  private void usingResultSelector() {
    Scanner in = new Scanner(System.in);
    System.out.println("Gugudan Input:");
    int dan = in.nextInt();
    // BiFunction 사용
    Observable<String> source = Observable.just(dan)
        .flatMap(num -> Observable.range(1, 9),
            (num, i) -> num + " * " + i + " = " + num * i);
    source.subscribe(System.out::println);
    in.close();
  }
}
