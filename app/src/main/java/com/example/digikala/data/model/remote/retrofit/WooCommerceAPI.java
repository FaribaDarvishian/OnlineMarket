package com.example.digikala.data.model.remote.retrofit;

import com.example.digikala.data.model.product.Product;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WooCommerceAPI {
    @GET("." + "products")
    Observable<List<Product>> getProducts(@QueryMap Map<String, String> options);
}

