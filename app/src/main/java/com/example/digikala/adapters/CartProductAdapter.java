package com.example.digikala.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digikala.R;
import com.example.digikala.data.model.product.Product;
import com.example.digikala.databinding.ListItemCartBinding;
import com.example.digikala.viewmodel.CartViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.CartProductHolder> {
    private List<Product> mItems;
    private CartViewModel mCartViewModel;

    public List<Product> getItems() {
        return mItems;
    }

    public CartProductAdapter(CartViewModel cartViewModel) {
        mCartViewModel = cartViewModel;
        mItems = new ArrayList<>();
    }

    public void setItems(List<Product> items) {
        mItems = items;
    }

    @NonNull
    @Override
    public CartProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemCartBinding listItemCartBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_cart,
                parent,
                false
        );
        return new CartProductHolder(listItemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductHolder holder, int position) {
        holder.bindCartProduct(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class CartProductHolder extends RecyclerView.ViewHolder {
        ListItemCartBinding mBinding;

        public CartProductHolder(@NonNull ListItemCartBinding listItemCartBinding) {
            super(listItemCartBinding.getRoot());
            mBinding=listItemCartBinding;
            mBinding.setViewModel(mCartViewModel);
        }

        public void bindCartProduct(Product product) {
            mBinding.setProduct(product);
            Picasso.get().load(product.getFeaturedImageUrl()).placeholder(R.drawable.logo).into(mBinding.cartProductImageView);
        }
    }
}
