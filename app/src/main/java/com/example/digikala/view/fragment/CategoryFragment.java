package com.example.digikala.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digikala.R;
import com.example.digikala.adapters.DefaultCategoryAdapter;
import com.example.digikala.data.model.product.Category;
import com.example.digikala.databinding.FragmentCategoryBinding;
import com.example.digikala.viewmodel.CategoriesViewModel;


import java.util.List;

public class CategoryFragment extends Fragment {
    public static final String TAG = "Category Fragment";
    private FragmentCategoryBinding mBinding;
    private CategoriesViewModel mViewModel;
    private DefaultCategoryAdapter mDefaultCategoryAdapter;

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
        mViewModel = new ViewModelProvider(requireActivity()).get(CategoriesViewModel.class);
        mViewModel.setAllCategories();
        mDefaultCategoryAdapter = new DefaultCategoryAdapter(this,mViewModel);

        mViewModel.getDefaultCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                mDefaultCategoryAdapter.setItems(categories);
                mDefaultCategoryAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_category,
                container,
                false
        );
        mBinding.defaultCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.defaultCategoriesRecyclerView.setAdapter(mDefaultCategoryAdapter);

        return mBinding.getRoot();
    }
}