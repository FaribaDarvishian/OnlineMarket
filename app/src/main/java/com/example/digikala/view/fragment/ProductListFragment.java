package com.example.digikala.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.digikala.R;
import com.example.digikala.data.model.Options;
import com.example.digikala.data.model.product.Product;
import com.example.digikala.databinding.FragmentProductListBinding;
import com.example.digikala.viewmodel.ProductListViewModel;
import com.example.digikala.view.fragment.ProductListFragment;
import java.util.List;


public class ProductListFragment extends Fragment {
    public static final String TAG = "Product List Fragment";
    public static final String ARGS_OPTIONS = "OPTIONS";
    private FragmentProductListBinding mBinding;
    private ProductListViewModel mViewModel;

    public ProductListFragment() {
        // Required empty public constructor
    }

    public static ProductListFragment newInstance() {
    ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        mViewModel.setOptions((Options) getArguments().getSerializable(ARGS_OPTIONS));
        mViewModel.setInitialData();
        mViewModel.getProductByOptions().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                Log.d(TAG, "onChanged: "+products.size());
                Log.d(TAG, "onChanged: "+products.get(0).getName());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_product_list,
                container,
                false);

        return mBinding.getRoot();
    }
}