package com.example.OnlineMarket.viewmodel;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.OnlineMarket.view.fragment.NotificationTimePickerFragment;
import com.example.OnlineMarket.view.fragment.SettingsFragment;

public class NotificationTimePickerViewModel extends ViewModel {

    public void setResult(NotificationTimePickerFragment notificationTimePickerFragment, long milliSecondsLeft) {
        Fragment targetFragment = notificationTimePickerFragment.getTargetFragment();
        Intent intent = new Intent();
        intent.putExtra(SettingsFragment.EXTRA_DELAY_EXACT_NOTIFICATION, milliSecondsLeft);
        targetFragment.onActivityResult(notificationTimePickerFragment.getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

}
