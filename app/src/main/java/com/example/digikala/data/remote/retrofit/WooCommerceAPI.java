package com.example.digikala.data.remote.retrofit;

import com.example.digikala.data.model.product.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WooCommerceAPI {

    @GET("products")
    Call<List<Product>> getLatestProducts(@QueryMap Map<String, String> options);

    @GET("products")
    Call<List<Product>> getAllProducts();

}

