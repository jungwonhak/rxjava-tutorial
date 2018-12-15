package com.lldong0.reactivejava.chapter02;

import com.lldong0.reactivejava.common.Order;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ObservableFromIterable {
  public void listExample() {
    List<String> names = new ArrayList<>();
    names.add("Jerry");
    names.add("William");
    names.add("Bob");

    Observable<String> source = Observable.fromIterable(names);
    source.subscribe(System.out::println);
  }

  public void setExample() {
    Set<String> cities = new HashSet<>();
    cities.add("Seoul");
    cities.add("London");
    cities.add("Paris");

    Observable<String> source = Observable.fromIterable(cities);
    source.subscribe(System.out::println);
  }

  public void blockingQueueExample() {
    BlockingQueue<Order> orderQueue = new ArrayBlockingQueue<>(100);
    orderQueue.add(new Order("ORD-1"));
    orderQueue.add(new Order("ORD-2"));
    orderQueue.add(new Order("ORD-3"));

    Observable<Order> source = Observable.fromIterable(orderQueue);
    source.subscribe(order -> System.out.println(order.getId()));
  }

  public static void main(String[] args){
    ObservableFromIterable demo = new ObservableFromIterable();
    demo.listExample();
    demo.setExample();
    demo.blockingQueueExample();
  }
}
