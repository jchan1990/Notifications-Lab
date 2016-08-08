package com.example.qube.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NotificationCompat.Builder mNotificationBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345, intent, 0);

        mNotificationBuilder = new NotificationCompat.Builder(this);
        mNotificationBuilder.setSmallIcon(R.drawable.ic_chocobo);
        mNotificationBuilder.setContentTitle("Notification Alert!");
        mNotificationBuilder.setContentText("My Notification Text");
        mNotificationBuilder.setPriority(Notification.PRIORITY_MAX);
        mNotificationBuilder.setContentIntent(pendingIntent);


        ConnectivityManager conMag = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMag.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            mNotificationBuilder.setAutoCancel(true);
            final NotificationCompat.BigPictureStyle pictureStyle = new NotificationCompat.BigPictureStyle();
            pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_chocobo));
            mNotificationBuilder.setStyle(pictureStyle);
            NotificationManagerCompat.from(MainActivity.this).notify(1, mNotificationBuilder.build());

        } else {
            mNotificationBuilder.setAutoCancel(false);
            Toast.makeText(MainActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
            final NotificationCompat.BigPictureStyle pictureStyle = new NotificationCompat.BigPictureStyle();
            pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_dialog_alert));
            mNotificationBuilder.setStyle(pictureStyle);
            NotificationManagerCompat.from(MainActivity.this).notify(1, mNotificationBuilder.build());
        }


    }
}