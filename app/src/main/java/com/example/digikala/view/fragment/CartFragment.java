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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.digikala.R;
import com.example.digikala.adapters.CartProductAdapter;
import com.example.digikala.data.model.product.Product;
import com.example.digikala.data.repository.CartRepository;
import com.example.digikala.data.room.entities.Cart;
import com.example.digikala.databinding.FragmentCartBinding;
import com.example.digikala.viewmodel.CartViewModel;

import java.util.List;


public class CartFragment extends Fragment {
    private FragmentCartBinding mBinding;
    private CartViewModel mViewModel;
    private CartProductAdapter mAdapter;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
       CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        mAdapter = new CartProductAdapter(mViewModel);
        mViewModel.getCartsLiveData().observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                mViewModel.setCartsSubject(carts);
                Log.d(CartRepository.TAG, "onChanged: " + carts.size());
                mBinding.setViewModel(mViewModel);
                mViewModel.setProductsList(carts);
            }
        });
        mViewModel.getProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                Log.d(CartRepository.TAG, "onChanged: product list size " + products.size());
                mBinding.setViewModel(mViewModel);
                mAdapter.setItems(products);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        mBinding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.cartRecyclerView.setAdapter(mAdapter);
        mBinding.setViewModel(mViewModel);

        return mBinding.getRoot();
    }
}