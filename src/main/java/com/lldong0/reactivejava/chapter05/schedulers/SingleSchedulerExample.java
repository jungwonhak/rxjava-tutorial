package com.lldong0.reactivejava.chapter05.schedulers;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SingleSchedulerExample {

  public void basic() {
    Observable<Integer> numbers = Observable.range(100, 5);
    Observable<String> chars = Observable.range(0, 5)
        .map(CommonUtils::numberToAlphabet);

    numbers.subscribeOn(Schedulers.single())
        .subscribe(Log::i);
    chars.subscribeOn(Schedulers.single())
        .subscribe(Log::i);
    CommonUtils.sleep(500);
    CommonUtils.exampleComplete();
  }

  public static void main(String[] args) {
    SingleSchedulerExample demo = new SingleSchedulerExample();
    demo.basic();
  }

}
