package com.example.digikala.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digikala.data.model.product.Product;
import com.example.digikala.data.repository.ProductRepository;

public class ProductDetailsViewModel extends ViewModel {
    private ProductRepository mRepository;
    private LiveData<Product> mSelectedProduct;

    public ProductDetailsViewModel() {
        mRepository = ProductRepository.getInstance();
        mSelectedProduct=new MutableLiveData<>();
        mSelectedProduct = mRepository.getSelectedProductLiveData();
    }

    public void setSelectedProduct(int productId) {
        mRepository.setSelectedProductLiveData(productId);

    }

    public LiveData<Product> getSelectedProduct() {
        mSelectedProduct=mRepository.getSelectedProductLiveData();
        return mRepository.getSelectedProductLiveData();
    }

    public int getNumberOfImages(){
        return mSelectedProduct.getValue().getImages().size();
    }
}
