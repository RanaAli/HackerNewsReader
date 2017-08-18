package com.hacker.reader.data.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RanaAli on 8/6/17.
 */

public class RestClient {
  public static final String BASE_URL = "https://hacker-news.firebaseio.com/v0/";

  private static Retrofit retrofit;

  static {
    // enable logging
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build();
  }

  public static <T> T getService(Class<T> serviceClass) {
    return retrofit.create(serviceClass);
  }
}
