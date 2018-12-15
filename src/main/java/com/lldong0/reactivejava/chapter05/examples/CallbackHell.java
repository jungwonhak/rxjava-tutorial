package com.lldong0.reactivejava.chapter05.examples;

import static com.lldong0.reactivejava.common.CommonUtils.GITHUB_ROOT;

import com.lldong0.reactivejava.common.Log;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallbackHell {

  private static final String FIRST_URL = "https://api.github.com/zen";
  private static final String SECOND_URL = GITHUB_ROOT + "/samples/callback_hell";

  private final OkHttpClient client = new OkHttpClient();

  private final static Logger logger = LoggerFactory.getLogger(CallbackHell.class);

  /**
   * 두번째 URL 호출
   * Start Callback Hell
   */
  private Callback onSuccess = new Callback() {
    @Override
    public void onFailure(Call call, IOException e) {
//      e.printStackTrace();

      // using logging framework(logback)
      logger.error("Exception happen", e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
      Log.i(response.body().string());
    }
  };

  /**
   * 첫번째 URL 호출이 성공시에 두번째 URL 호출
   * onResponse 에서 callback 추가
   */
  private void run() {
    Request request = new Request.Builder()
        .url(FIRST_URL)
        .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
//        e.printStackTrace();

        logger.error("Exception happen", e);
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        Log.i(response.body().string());

        //add callback again
        Request request = new Request.Builder()
            .url(SECOND_URL)
            .build();
        client.newCall(request).enqueue(onSuccess);
      }
    });
  }

  public static void main(String[] args) {
    CallbackHell demo = new CallbackHell();
    demo.run();
  }
}
