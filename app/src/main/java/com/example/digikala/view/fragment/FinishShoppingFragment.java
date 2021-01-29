package com.example.digikala.view.fragment;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.digikala.R;
import com.example.digikala.adapters.AddressAdapter;
import com.example.digikala.data.model.coupon.Coupon;
import com.example.digikala.data.repository.AddressRepository;
import com.example.digikala.data.repository.CartRepository;
import com.example.digikala.data.room.entities.Cart;
import com.example.digikala.data.room.entities.MyAddress;
import com.example.digikala.databinding.FragmentFinishShoppingBinding;
import com.example.digikala.viewmodel.FinishShoppingViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.digikala.utils.SnakeBar.showAddSnakeBar;

public class FinishShoppingFragment extends Fragment {
    public static final String ARG_TOTAL_PRICE = "totalPrice";
    public static final String TAG = "Finish ShoppingFragment";
    private FragmentFinishShoppingBinding mBinding;
    private FinishShoppingViewModel mViewModel;
    private AddressAdapter mAddressAdapter;
    private NavController mNavController;


    public FinishShoppingFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FinishShoppingViewModel.class);
        mAddressAdapter = new AddressAdapter(mViewModel);
        setInitData();
        setObservers();

    }

    private void setInitData() {
        mViewModel.fetchCouponsLiveData();
        mViewModel.setBasePrice(getArguments().getLong(ARG_TOTAL_PRICE));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setObservers() {
        mViewModel.getAddresses().observe(this, myAddresses -> {
            Log.d(TAG, "onChanged: " + myAddresses.size());
            mAddressAdapter.setItems(myAddresses);
            mAddressAdapter.notifyDataSetChanged();
            mBinding.setViewModel(mViewModel);
        });
        mViewModel.getTotalPrice().observe(this, aLong -> mBinding.setViewModel(mViewModel));

        mViewModel.fetchCartsLiveData().observe(this, carts -> mViewModel.setCartsSubject(carts));
        mViewModel.getOrderState().observe(this, s -> {
            if (s.equals("ok")) {
                Snackbar snackbar = Snackbar.make(mBinding.getRoot(), "سفارش شما با موفقیت ثبت شد.", BaseTransientBottomBar.LENGTH_LONG);
                showAddSnakeBar(snackbar, getActivity());
                mNavController.navigateUp();
            }
            mBinding.setViewModel(mViewModel);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_finish_shopping, container, false);
        initUi();

        setListeners();

        return mBinding.getRoot();
    }

    private void initUi() {
        mBinding.addressRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.addressRecyclerView.setAdapter(mAddressAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setListeners() {
        mBinding.addAddressButton.setOnClickListener(v -> mNavController
                .navigate(R.id.action_finishShoppingFragment_to_addAddressMapsFragment));
        mBinding.registerCouponTextView.setOnClickListener(v -> {
            if (mBinding.editTextCoupon.getText() == null) {
                mViewModel.setCouponUsed(false);
                mViewModel.setBasePrice(getArguments().getLong(ARG_TOTAL_PRICE));
                return;
            }
            if (mViewModel.checkCoupon(mBinding.editTextCoupon.getText().toString()))
                mBinding.setViewModel(mViewModel);
            else {
                Snackbar snackbar = Snackbar.make(mBinding.getRoot(), "متاسفانه کد صحیح نمی باشد", BaseTransientBottomBar.LENGTH_LONG);
                showAddSnakeBar(snackbar, getActivity());
                mViewModel.setCouponUsed(false);
                mViewModel.setBasePrice(getArguments().getLong(ARG_TOTAL_PRICE));
            }
            mBinding.setViewModel(mViewModel);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
    }

    @Override
    public void onStop() {
        mViewModel.deselectAllAddresses();
        super.onStop();
    }
}