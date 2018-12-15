package com.lldong0.reactivejava.chapter05.examples;

import static com.lldong0.reactivejava.common.CommonUtils.API_KEY;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import com.lldong0.reactivejava.common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OpenWeatherMapV1 {

  private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=";

  public void run() {
    Observable<String> source = Observable.just(URL + API_KEY)
        .map(OkHttpHelper::getWithLog)
        .subscribeOn(Schedulers.io());

    //어떻게 호출을 한번만 하게 할 수 있을까?
    Observable<String> temperature = source.map(this::parseTemperatureV2);
    Observable<String> city = source.map(this::parseCityNameV2);
    Observable<String> country = source.map(this::parseCountryV2);

    CommonUtils.exampleStart();
    // 정보 취합
    Observable.concat(temperature,
        city,
        country)
        .doOnNext(data -> Log.v("parsed data : " + data))
        .observeOn(Schedulers.newThread())
        .subscribe(Log::it);

    CommonUtils.sleep(1000);
  }

  private String parseTemperature(String json) {
    return parse(json, "\"temp\":[0-9]*.[0-9]*");
  }

  private String parseCityName(String json) {
    return parse(json, "\"name\":\"[a-zA-Z]*\"");
  }

  private String parseCountry(String json) {
    return parse(json, "\"country\":\"[a-zA-Z]*\"");
  }

  private String parse(String json, String regex) {
    Pattern pattern = Pattern.compile(regex); // 찾고자하는 패턴 생성
    Matcher match = pattern.matcher(json); // 매칭 인스턴스 생성
    if (match.find()) {
      return match.group();
    }
    return "N/A";
  }

  private String parseTemperatureV2(String json) {
    JsonObject object = parseV2(json);
    return object.getAsJsonObject("main").get("temp").getAsString();
  }

  private String parseCityNameV2(String json) {
    JsonObject object = parseV2(json);
    return object.get("name").getAsString();
  }

  private String parseCountryV2(String json) {
    JsonObject object = parseV2(json);
    return object.getAsJsonObject("sys").get("country").getAsString();
  }


  private JsonObject parseV2(String json) {
    JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
    return jsonObject;
  }

  public static void main(String[] args) {
    OpenWeatherMapV1 demo = new OpenWeatherMapV1();
    demo.run();
  }
}
