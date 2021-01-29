package com.example.OnlineMarket.view.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;


import com.example.OnlineMarket.databinding.ActivityMainPageBinding;
import com.example.OnlineMarket.view.fragment.MainPageFragment;

public class MainPageActivity extends SingleFragmentActivity {
    private ActivityMainPageBinding mBinding;

   // private NavController mNavController;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainPageActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return MainPageFragment.newInstance();
    }

/*    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_OnlineMarket);
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main_page);

        mNavController= Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(mBinding.bottomNavigation,mNavController);

    }*/
}