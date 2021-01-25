package com.example.digikala.view.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.example.digikala.view.fragment.MainPageFragment;

public class MainPageActivity extends SingleFragmentActivity {


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainPageActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return MainPageFragment.newInstance();
    }


}