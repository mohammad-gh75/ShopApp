package org.maktab36.finalproject;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class ShopAppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String id = getResources().getString(R.string.notification_channel_id);
            String name = getResources().getString(R.string.notification_channel_name);
            String description = getResources().getString(R.string.notification_channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(id, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
