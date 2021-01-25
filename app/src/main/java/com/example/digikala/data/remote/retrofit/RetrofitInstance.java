package com.example.digikala.data.remote.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static  com.example.digikala.data.remote.NetworkParams.BASE_URL;

public class RetrofitInstance {
    private static Retrofit sRetrofit;

    public static Retrofit getInstance() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
    public RetrofitInstance() {
    }
}
