package com.lldong0.reactivejava.chapter03;

import com.lldong0.reactivejava.common.CommonUtils;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class QueryTvSales {

  public static void main(String[] args) {
    QueryTvSales tvSales = new QueryTvSales();
    tvSales.run();
  }

  private void run(){
    //1. 데이터 입력
    List<Pair<String,Integer>> sales = new ArrayList<>();
    sales.add(Pair.of("TV", 2500));
    sales.add(Pair.of("Camera", 300));
    sales.add(Pair.of("TV", 1600));
    sales.add(Pair.of("Phone", 800));
    sales.add(Pair.of("TV", 800));

    Maybe<Integer> tvSales = Observable.fromIterable(sales)
        //2. 매출 데이터중 TV 매출을 필터링 함
        .filter(sale -> "TV".equals(sale.getLeft()))
        .map(sale -> sale.getRight())
        //3. TV 매출의 합을 구함
        .reduce((sale1, sale2) -> sale1 + sale2);
    tvSales.subscribe(tot -> System.out.println("TV Sales: $" + tot));
    CommonUtils.exampleComplete();
  }

}
