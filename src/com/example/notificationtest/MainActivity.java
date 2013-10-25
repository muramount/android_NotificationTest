
package com.example.notificationtest;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class MainActivity extends Activity {

    private NotificationManager mNotificationManager;
    private static int NOTIFICATION_NUMBER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_main);

        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Button button = new Button(this);
        button.setText("send notification");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
        addContentView(button, new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    }

    private void sendNotification() {
        Intent intent = new Intent();//dummy
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setContentIntent(pendingIntent);
        builder.setTicker("ステータスバーに表示されるテキスト");
        builder.setSmallIcon(R.drawable.ic_launcher);//通知バーアイコン
        builder.setContentTitle("Notification 開いた時のタイトル");
        builder.setContentText("Notificaton 開いた時のメッセージ");
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));//Notification 開いた時のアイコン
        builder.setWhen(System.currentTimeMillis());//通知タイミング
        builder.setAutoCancel(true);//タップでキャンセル

        mNotificationManager.notify(NOTIFICATION_NUMBER, builder.build());

        NOTIFICATION_NUMBER++;
    }

}
