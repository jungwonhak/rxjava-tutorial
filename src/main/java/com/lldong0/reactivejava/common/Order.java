package com.lldong0.reactivejava.common;

public class Order {

  private String id;

  public Order(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Order{" +
        "id='" + id + '\'' +
        '}';
  }
}
