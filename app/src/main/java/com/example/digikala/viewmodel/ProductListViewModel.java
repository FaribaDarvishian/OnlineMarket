package com.example.digikala.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.digikala.data.model.Options;
import com.example.digikala.data.model.product.Product;
import com.example.digikala.data.repository.ProductRepository;

import java.util.List;

public class ProductListViewModel extends ViewModel {
    private ProductRepository mRepository;
    private Options mOptions;

    public ProductListViewModel() {
        mRepository = ProductRepository.getInstance();
    }

    public Options getOptions() {
        return mOptions;
    }

    public void setOptions(Options options) {
        mOptions = options;
    }

    public void setInitialData() {
        mRepository.setProductByOptionsLiveData(mOptions);
    }

    public LiveData<List<Product>> getProductByOptions() {
        return mRepository.getProductByOptionsLiveData();
    }

}