package com.example.OnlineMarket.data.repository;

import android.util.Log;


import com.example.OnlineMarket.data.model.customer.Customer;
import com.example.OnlineMarket.data.remote.NetworkParams;
import com.example.OnlineMarket.data.remote.retrofit.RetrofitInstance;
import com.example.OnlineMarket.data.remote.retrofit.WooCommerceAPI;
import com.example.OnlineMarket.view.fragment.ProfileFragment;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

public class CustomerRepository {
    private static CustomerRepository sInstance;

    private WooCommerceAPI mWooCommerceAPI;

    public static CustomerRepository getCustomer() {
        if (sInstance == null)
            sInstance = new CustomerRepository();
        return sInstance;
    }

    private CustomerRepository() {
        mWooCommerceAPI = RetrofitInstance.getInstance().create(WooCommerceAPI.class);

    }


        public Call<List<Customer>> setCustomerLiveData(String email) {
        Log.d(ProfileFragment.TAG, "onResponse: setCustomer " + email);
            return mWooCommerceAPI.getCustomers(NetworkParams.getCustomer(email));
    }

    public Call<Customer> registerCustomer(Customer customer) {
        return mWooCommerceAPI.postCustomers(new HashMap<>(), customer);
    }
}
