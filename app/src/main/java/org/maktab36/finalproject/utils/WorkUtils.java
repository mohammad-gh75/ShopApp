package org.maktab36.finalproject.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.data.repository.ProductRepository;
import org.maktab36.finalproject.view.activity.NotificationNewProductsActivity;

import java.util.ArrayList;
import java.util.List;

public class WorkUtils {

    private static final int REQUEST_CODE_NOTIFICATION = 1;
    private static final int NOTIFICATION_ID = 0;

    public static void pollFromServerAndNotify(Context context, String tag) {
        ProductRepository repository = ProductRepository.getInstance();
        int lastResultIdPref = SharedPreferencesUtils.getLastResultId(context);
        List<Product> products = repository.getLastProductsSync();


        if (products == null || products.size() == 0)
            return;

        ArrayList<Product> newProducts = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() != lastResultIdPref) {
                newProducts.add(products.get(i));
            } else {
                break;
            }
        }

        Log.d(tag, "lastResultIdPref: " + lastResultIdPref);

        if (!newProducts.isEmpty()) {
            showNotification(context, newProducts);
            Log.d(tag, "show notification");
            SharedPreferencesUtils.setLastResultId(context, newProducts.get(0).getId());
        } else {
            Log.d(tag, "do nothing");
        }
    }

    private static void showNotification(Context context, ArrayList<Product> newProducts) {
        String channelId = context.getResources().getString(R.string.notification_channel_id);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                REQUEST_CODE_NOTIFICATION,
                NotificationNewProductsActivity.newIntent(context, newProducts),
                0);

        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_new_product)
                .setContentTitle(context.getResources().getString(R.string.new_product_title))
                .setContentText(context.getResources().getString(R.string.new_product_text))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }
}
