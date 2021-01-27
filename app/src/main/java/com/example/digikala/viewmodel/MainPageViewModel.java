package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.digikala.data.model.product.Product;
import com.example.digikala.data.repository.ProductRepository;
import com.example.digikala.data.model.Options;

import java.util.ArrayList;
import java.util.List;

public class MainPageViewModel extends ViewModel {
    private ProductRepository mRepository;


    public MainPageViewModel() {
        mRepository = ProductRepository.getInstance();
    }

    public LiveData<List<Product>> getLatestProducts(){
        return mRepository.getLatestProductsLiveData();
    }


    public void setLatestProducts(){
        mRepository.setLatestProductsLiveData();
    }

    public LiveData<List<Product>> getPopularProducts() {
        return mRepository.getPopularProductsLiveData();
    }

    public void setInitialData() {
        setLatestProducts();
        setPopularProducts();
        setTopRatedProducts();
    }
    public void setSearchedProducts(String query) {
        Options options = new Options(query);
        mRepository.setProductByOptionsLiveData(options);
    }

    public LiveData<List<Product>> getSearchedProducts() {
        return mRepository.getProductByOptionsLiveData();
    }

    private void setTopRatedProducts() {
        mRepository.setTopRatedProductsLiveData();
    }

    public LiveData<List<Product>> getTopRatedProducts() {
        return mRepository.getTopRatedProductsLiveData();
    }

    private void setPopularProducts() {
        mRepository.setPopularProductsLiveData();
    }
}