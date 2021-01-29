package com.example.digikala.viewmodel;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.digikala.view.fragment.NotificationTimePickerFragment;

import static com.example.digikala.view.fragment.SettingsFragment.EXTRA_DELAY_EXACT_NOTIFICATION;

public class NotificationTimePickerViewModel extends ViewModel {

    public void setResult(NotificationTimePickerFragment notificationTimePickerFragment, long milliSecondsLeft) {
        Fragment targetFragment = notificationTimePickerFragment.getTargetFragment();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DELAY_EXACT_NOTIFICATION, milliSecondsLeft);
        targetFragment.onActivityResult(notificationTimePickerFragment.getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

}
