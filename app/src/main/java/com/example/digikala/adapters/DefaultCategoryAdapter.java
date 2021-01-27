package com.example.digikala.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digikala.R;
import com.example.digikala.data.model.product.Category;
import com.example.digikala.databinding.ListItemDefaultCategoryBinding;
import com.example.digikala.viewmodel.CategoriesViewModel;

import java.util.ArrayList;
import java.util.List;

public class DefaultCategoryAdapter extends RecyclerView.Adapter<com.example.digikala.adapters.DefaultCategoryAdapter.DefaultCategoryHolder> {
    private static final String TAG = "Defaul Category Adapter";
    private List<Category> mItems;
    private CategoriesViewModel mCategoriesViewModel;
    private LifecycleOwner mOwner;


    public List<Category> getItems() {
        return mItems;
    }

    public void setItems(List<Category> items) {
        mItems = items;
    }

    public DefaultCategoryAdapter(LifecycleOwner owner, CategoriesViewModel viewModel) {
        mItems = new ArrayList<>();
        mOwner = owner;
        mCategoriesViewModel = viewModel;
    }

    @NonNull
    @Override
    public DefaultCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemDefaultCategoryBinding listItemDefaultCategoryBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_default_category,
                parent,
                false
        );
        return new DefaultCategoryHolder(listItemDefaultCategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultCategoryHolder holder, int position) {
        holder.bindDefaultCategory(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class DefaultCategoryHolder extends RecyclerView.ViewHolder {
        ListItemDefaultCategoryBinding mBinding;
        public com.example.digikala.adapters.SubCategoryAdapter mSubCategoryAdapter;

        public DefaultCategoryHolder(ListItemDefaultCategoryBinding listItemDefaultCategoryBinding) {
            super(listItemDefaultCategoryBinding.getRoot());
            mBinding = listItemDefaultCategoryBinding;
            mBinding.subCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(mCategoriesViewModel.getApplication(), RecyclerView.HORIZONTAL, true));
            mSubCategoryAdapter = new com.example.digikala.adapters.SubCategoryAdapter();
            mBinding.subCategoryRecyclerView.setAdapter(mSubCategoryAdapter);
        }

        public void bindDefaultCategory(Category category) {
            mBinding.setCategory(category);
            mBinding.executePendingBindings();
            Log.d(TAG, "bindDefaultCategory: " + category.getCount());
            List<Category> subCategories = new ArrayList<>();
            mCategoriesViewModel.getSubCategories().observe(mOwner,
                    new Observer<List<Category>>() {
                        @Override
                        public void onChanged(List<Category> categories) {
                            for (int i = 0; i < categories.size(); i++) {
                                if (categories.get(i).getParent() == category.getId())
                                    subCategories.add(categories.get(i));
                            }
                            if (subCategories.size() == 0)
                                subCategories.add(category);
                            mSubCategoryAdapter.setItems(subCategories);
                            mSubCategoryAdapter.notifyDataSetChanged();
                        }
                    });
        }
    }
}
