package com.lldong0.reactivejava.chapter04.create;

import static com.lldong0.reactivejava.common.Shape.BLUE;
import static com.lldong0.reactivejava.common.Shape.GREEN;
import static com.lldong0.reactivejava.common.Shape.RED;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import com.lldong0.reactivejava.common.OkHttpHelper;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class RepeatExample {

  public void marbleDiagram() {
    String[] balls = {RED, GREEN, BLUE}; // 1, 3, 5
    Observable<String> source = Observable.fromArray(balls)
        .repeat(3);

    source.doOnComplete(() -> Log.d("onComplete"))
        .subscribe(Log::i);
    CommonUtils.exampleComplete();
  }

  private void heartbeatV1() {
    CommonUtils.exampleStart();
    String serverUrl = "https://api.github.com/zen";

    Observable.timer(2, TimeUnit.SECONDS)    //2초 간격으로 서버에 ping 날리기
        .map(val -> serverUrl)
        .map(OkHttpHelper::get)
        .repeat()
        .subscribe(res -> Log.it("Ping Result : " + res));
    CommonUtils.sleep(10000);
    CommonUtils.exampleComplete();
  }

  private void heartbeatV2() {
    CommonUtils.exampleStart();
    String serverUrl = "https://api.github.com/zen";

    Observable.interval(2L, TimeUnit.SECONDS)
        .map(var -> serverUrl)
        .map(OkHttpHelper::get)
        .take(4)
        .subscribe(res -> Log.it("Ping Result : " + res));
    CommonUtils.sleep(10000);
    CommonUtils.exampleComplete();
  }


  public static void main(String[] args) {
    RepeatExample demo = new RepeatExample();
    demo.marbleDiagram();
    demo.heartbeatV1();
    demo.heartbeatV2();
  }
}
