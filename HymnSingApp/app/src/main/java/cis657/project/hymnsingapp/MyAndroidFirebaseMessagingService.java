package cis657.project.hymnsingapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyAndroidFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "DBG";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage == null)
            return;

        // check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage);
        }

        // check if message contains a data payload
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "From: " + remoteMessage.getFrom());
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }
    }

    private void sendNotification(RemoteMessage remoteMessage) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        //Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

// this is a my insertion looking for a solution
        int icon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? R.drawable.ic_stat_notifications: R.mipmap.ic_launcher;
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"M_CH_ID")
                .setSmallIcon(icon)
                .setContentTitle(remoteMessage.getFrom())
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
