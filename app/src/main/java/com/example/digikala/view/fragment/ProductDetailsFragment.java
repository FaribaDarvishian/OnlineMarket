package com.example.digikala.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digikala.R;
import com.example.digikala.adapters.ImageSliderAdapter;
import com.example.digikala.data.model.product.Product;
import com.example.digikala.databinding.FragmentProductDetailsBinding;
import com.example.digikala.databinding.ListItemProductBinding;

public class ProductDetailsFragment extends Fragment {
    private FragmentProductDetailsBinding mBinding;
    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_product_details,
                container,
                false
        );
       // mBinding.imageViewPager.
                Product product= (Product) getArguments().getSerializable("product");
        ImageSliderAdapter adapter=new ImageSliderAdapter(product.getImages());
        mBinding.imageViewPager.setAdapter(adapter);

        return mBinding.getRoot();
    }
    }
