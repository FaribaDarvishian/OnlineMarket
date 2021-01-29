package com.example.OnlineMarket.data.repository;

import android.util.Log;

import com.example.OnlineMarket.data.remote.retrofit.RetrofitInstance;
import com.example.OnlineMarket.data.remote.retrofit.WooCommerceAPI;
import com.example.OnlineMarket.data.model.product.Category;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

public class CategoryRepository {
    public static final String TAG = "Category Repository";
    private static CategoryRepository sInstance;
    private WooCommerceAPI mWooCommerceAPI;


    public static CategoryRepository getInstance() {
        if (sInstance == null)
            sInstance = new CategoryRepository();
        return sInstance;
    }

    public CategoryRepository() {
        mWooCommerceAPI = RetrofitInstance.getInstance().create(WooCommerceAPI.class);
    }


    public Call<List<Category>> setAllCategoriesItems() {
        Log.d(TAG, "setAllCategoriesItems: ");
        return mWooCommerceAPI.getCategories(new HashMap<>());
    }
}
