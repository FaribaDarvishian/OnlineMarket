package com.example.digikala.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.digikala.R;
import com.example.digikala.databinding.FragmentCategoryBinding;


public class CategoryFragment extends Fragment {
    private FragmentCategoryBinding mBinding;


    public CategoryFragment() {
        // Required empty public constructor
    }

    public static com.example.digikala.view.fragment.CategoryFragment newInstance() {
        com.example.digikala.view.fragment.CategoryFragment fragment = new com.example.digikala.view.fragment.CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_category,
                container,
                false
        );

        return mBinding.getRoot();
    }
}