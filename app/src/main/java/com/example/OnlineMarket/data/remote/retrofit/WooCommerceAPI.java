package com.example.OnlineMarket.data.remote.retrofit;
import com.example.OnlineMarket.data.model.customer.Customer;
import com.example.OnlineMarket.data.model.order.Order;
import com.example.OnlineMarket.data.model.product.Category;
import com.example.OnlineMarket.data.model.product.Product;
import com.example.OnlineMarket.data.model.coupon.Coupon;
import com.example.OnlineMarket.data.model.review.Review;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface WooCommerceAPI {

    @GET("products")
    Call<List<Product>> getProducts(@QueryMap Map<String, String> options);
    @GET("products")
    Call<List<Product>> getAllProducts();
    @GET("products")
    Call<List<Product>> getPopularProduct();

    @GET("products/{productId}")
    Call<Product> getProductById(@Path("productId") int productId);

    @GET("products/categories?per_page=100")
    Call<List<Category>> getCategories(@QueryMap Map<String, String> options);

    @GET("customers")
    Call<List<Customer>> getCustomers(@QueryMap Map<String, String> options);

    @POST("orders")
    Call<Order> postOrder(@Body Order order);

    @POST("customers")
    Call<Customer> postCustomers(@QueryMap Map<String, String> options, @Body Customer customer );
    @GET("coupons")
    Call<List<Coupon>> getCoupons(@QueryMap Map<String, String> options);

    @GET("products/reviews")
    Call<List<Review>> getReviews(@QueryMap Map<String, String> options);

}
