package com.lldong0.reactivejava.chapter05.examples;


import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpGetExample {

  private final OkHttpClient client = new OkHttpClient();

  private static final String URL_README =
      "https://raw.githubusercontent.com/yudong80/reactivejava/master/README.md";

  private static final Logger logger = LoggerFactory.getLogger(HttpGetExample.class);

  private void run() {
    Request request = new Request.Builder()
        .url(URL_README)
        .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
//        e.printStackTrace();
        // using logback
        logger.error("Exception Happen", e);
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        Log.i(response.body().string());
      }
    });
    CommonUtils.exampleComplete();
  }

  public static void main(String[] args) {
    HttpGetExample demo = new HttpGetExample();
    demo.run();
  }
}
