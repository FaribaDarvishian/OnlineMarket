package com.example.digikala.view.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;

import com.example.digikala.R;
import com.example.digikala.databinding.ActivityMainPageBinding;
import com.example.digikala.databinding.FragmentMainPageBinding;
import com.example.digikala.view.fragment.MainPageFragment;

import com.example.digikala.view.fragment.MainPageFragment;

public class MainPageActivity extends AppCompatActivity {
    private ActivityMainPageBinding mBinding;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainPageActivity.class);
        return intent;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_Digikala);
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_page);

    }
}