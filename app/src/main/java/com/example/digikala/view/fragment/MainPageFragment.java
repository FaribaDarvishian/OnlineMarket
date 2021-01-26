package com.example.digikala.view.fragment;

import androidx.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import android.database.DatabaseUtils;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.digikala.R;
import com.example.digikala.adapter.ProductAdapter;
import com.example.digikala.data.model.product.Product;
import com.example.digikala.data.remote.NetworkParams;
import com.example.digikala.data.remote.retrofit.RetrofitInstance;
import com.example.digikala.data.remote.retrofit.WooCommerceAPI;
import com.example.digikala.databinding.FragmentMainPageBinding;
import com.example.digikala.viewmodel.MainPageViewModel;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPageFragment extends Fragment {
    private FragmentMainPageBinding mBinding;
    private ProductAdapter mLatestAdapter;
    private MainPageViewModel mViewModel;

    public MainPageFragment() {
        // Required empty public constructor
    }

    public static MainPageFragment newInstance() {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity()).get(MainPageViewModel.class);

        mViewModel.getLatestProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                Log.d("MPF", "set Observer "+products.get(products.size() - 1).getName());
                updateUI();
            }
        });

        mViewModel.setLatestProducts();

    }

    private void updateUI() {
        if (mLatestAdapter == null) {
            mLatestAdapter = new ProductAdapter(getActivity(), mViewModel.getLatestProducts().getValue());
            mBinding.newProductsRecyclerView.setAdapter(mLatestAdapter);
        } else {
            mLatestAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_main_page,
                container,
                false);

        mBinding.newProductsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));

/*        mWooCommerceAPI.getAllProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mLatestProductsLiveData.setValue(response.body());
                    ProductAdapter adapter = new ProductAdapter(getActivity(), mLatestProductsLiveData.getValue());
                    mBinding.newProductsRecyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });*/


/*        mWooCommerceAPI.getAllProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Product>>() {
                    @Override
                    public void accept(List<Product> products) {
                        ProductAdapter adapter = new ProductAdapter(getActivity(), products);
                        mBinding.newProductsRecyclerView.setAdapter(adapter);
                    }






        @Override
        public void onFailure(Call<List<Product>> call, Throwable t) {

        }
    });*/

        return mBinding.getRoot();
    }
}