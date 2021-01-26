package com.example.digikala.view.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import android.database.DatabaseUtils;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.digikala.R;
import com.example.digikala.adapter.ProductAdapter;
import com.example.digikala.data.model.product.Product;
import com.example.digikala.databinding.FragmentMainPageBinding;
import com.example.digikala.viewmodel.MainPageViewModel;
import java.util.List;



public class MainPageFragment extends Fragment implements ProductAdapter.OnProductListener {
    public static final String TAG = "Main Page Fragment";
    private FragmentMainPageBinding mBinding;
    private ProductAdapter mLatestAdapter;
    private MainPageViewModel mViewModel;
    private ProductAdapter mPopularAdapter;
    private ProductAdapter mTopRatedAdapter;
    private MainPageFragment mListener=this;
    private NavController mNavController;

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

        setObservers();
        mViewModel.setInitialData();


    }
    private void setObservers() {
        mViewModel.getLatestProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                Log.d(TAG, "set latest Observer " + products.get(products.size() - 1).getName());
                if (mLatestAdapter == null) {
                    mLatestAdapter = new ProductAdapter(mViewModel.getLatestProducts().getValue(),mListener);
                    mBinding.newProductsRecyclerView.setAdapter(mLatestAdapter);
                } else {
                    mLatestAdapter.notifyDataSetChanged();
                }
            }
        });
        mViewModel.getPopularProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                Log.d(TAG, "set popular Observer " + products.get(products.size() - 1).getName());
                if (mPopularAdapter == null) {
                    mPopularAdapter = new ProductAdapter(mViewModel.getPopularProducts().getValue(),mListener);
                    mBinding.popularRecyclerView.setAdapter(mPopularAdapter);
                } else {
                    mPopularAdapter.notifyDataSetChanged();
                }
            }
        });
        mViewModel.getTopRatedProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                Log.d(TAG, "set top rated Observer " + products.get(products.size() - 1).getName());
                if (mTopRatedAdapter == null) {
                    mTopRatedAdapter = new ProductAdapter(mViewModel.getTopRatedProducts().getValue(),mListener);
                    mBinding.topRatedRecyclerView.setAdapter(mTopRatedAdapter);
                } else {
                    mTopRatedAdapter.notifyDataSetChanged();
                }
            }
        });


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

        mBinding.popularRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        mBinding.topRatedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController=Navigation.findNavController(view);
    }
    @Override
    public void onProductClicked(Product product) {
        Log.d(TAG, "onProductClicked: "+product.getName());
        mNavController.navigate(R.id.action_mainPageFragment_to_productDetailsFragment);
    }
}