package com.example.OnlineMarket.worker;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavDeepLinkBuilder;

import com.example.OnlineMarket.data.model.product.Product;
import com.example.OnlineMarket.data.repository.ProductRepository;
import com.example.OnlineMarket.utils.QueryPreferences;
import com.example.OnlineMarket.view.activity.MainPageActivity;
import com.example.OnlineMarket.view.fragment.ProductDetailsFragment;
import com.example.OnlineMarket.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PollAndNotify {
    public static final String TAG = "PollAndNotify";
    private static final int NOTIFICATION_ID = 0;

    public static void pollFromServerAndNotify(Context context) {
        ProductRepository repository = ProductRepository.getInstance();
        List<Product> latestProducts = new ArrayList<>();
        try {
            latestProducts = repository.getLatestProduct().execute().body();
            Log.d(TAG, "pollFromServerAndNotify: " + latestProducts.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (latestProducts.size() == 0)
            return;
//        if (latestProducts.get(0).getId() != QueryPreferences.getLastProductId(context)) {
        showNotification(context, latestProducts.get(0));
//        }
        QueryPreferences.setLastProductId(context, latestProducts.get(1).getId());
    }


    private static void showNotification(Context context, Product product) {
        String channelId = context.getResources().getString(R.string.channel_id);
        Bundle bundle = new Bundle();
        bundle.putInt(ProductDetailsFragment.ARG_PRODUCT_ID, product.getId());
        bundle.putString(ProductDetailsFragment.ARG_PRODUCT_NAME, product.getName());
        PendingIntent pendingIntent = new NavDeepLinkBuilder(context).
                setComponentName(MainPageActivity.class)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.productDetailsFragment)
                .setArguments(bundle)
                .createPendingIntent();

        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(context.getResources().getString(R.string.new_product_added_title))
                .setContentText(context.getResources().getString(R.string.new_product_added_text))
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }

}
