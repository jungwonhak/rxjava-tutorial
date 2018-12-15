package com.lldong0.reactivejava.chapter02;

import io.reactivex.Observable;
import java.util.stream.IntStream;

public class ObservableFromArray {

  public static void main(String[] args) {
    ObservableFromArray demo = new ObservableFromArray();
    demo.integerArray();
  }

  private void integerArray() {
    int[] arr = {110, 210, 310};
    Observable<Integer> intSource = Observable.fromArray(toIntegerArray(arr));
    intSource.subscribe(System.out::println);

    Integer[] integers = {100, 200, 300};
    Observable<Integer> source = Observable.fromArray(integers);
    source.subscribe(System.out::println);

  }

  private Integer[] toIntegerArray(int[] intArray) {
    return IntStream.of(intArray).boxed().toArray(Integer[]::new);
  }
}
