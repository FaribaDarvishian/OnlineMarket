package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.customer.Customer;
import com.example.digikala.data.repository.CustomerRepository;

public class ProfileViewModel extends AndroidViewModel {
    private CustomerRepository mRepository;
    LiveData<Customer> mCustomerLiveData;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        mRepository = CustomerRepository.getCustomer();
        mCustomerLiveData = new MutableLiveData<>();
        mCustomerLiveData = mRepository.getCustomerLiveData();
    }

    public LiveData<Customer> getCustomerLiveData() {
        return mCustomerLiveData;
    }

    public void setCustomerLiveData(String email) {
        mRepository.setCustomerLiveData(email);
    }

    public boolean isCustomerReady() {
        return mCustomerLiveData.getValue() == null ? false : true;
    }



}
