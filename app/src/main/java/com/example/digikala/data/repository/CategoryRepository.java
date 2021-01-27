package com.example.digikala.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.product.Category;
import com.example.digikala.data.remote.NetworkParams;
import com.example.digikala.data.remote.retrofit.RetrofitInstance;
import com.example.digikala.data.remote.retrofit.WooCommerceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    public static final String TAG = "Category Repository";
    private static com.example.digikala.data.repository.CategoryRepository sInstance;
    private WooCommerceAPI mWooCommerceAPI;

    private final MutableLiveData<List<Category>> mCategoriesItems;

    public static com.example.digikala.data.repository.CategoryRepository getInstance() {
        if (sInstance == null)
            sInstance = new com.example.digikala.data.repository.CategoryRepository();
        return sInstance;
    }

    public CategoryRepository() {
        mWooCommerceAPI = RetrofitInstance.getInstance().create(WooCommerceAPI.class);
        mCategoriesItems = new MutableLiveData<>();
    }

    public MutableLiveData<List<Category>> getCategoriesItems() {
        return mCategoriesItems;
    }

    public void setAllCategoriesItems() {
        Log.d(TAG, "setAllCategoriesItems: ");
        mWooCommerceAPI.getCategories(NetworkParams.BASE_OPTIONS)
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        mCategoriesItems.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {

                    }
                });
    }
}
