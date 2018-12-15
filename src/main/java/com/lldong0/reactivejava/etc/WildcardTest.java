package com.lldong0.reactivejava.etc;

import java.util.Arrays;
import java.util.List;

public class WildcardTest {

  public static void main(String[] args) {
    List<Integer> integerList = Arrays.asList(1, 2, 3);
    System.out.println("Integer" + sumOfList(integerList));


    List<Double> doubleList = Arrays.asList(1.0, 2.0, 3.0);
    System.out.println("Double" + sumOfList(doubleList));
  }

  private static int sumOfList(List<? extends Number> list) {
    int sum = 0;
    for (Number i : list) {
      sum += i.intValue();
    }
    return sum;
  }

}
