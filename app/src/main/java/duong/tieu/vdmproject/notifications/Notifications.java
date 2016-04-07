package duong.tieu.vdmproject.notifications;


import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import duong.tieu.vdmproject.R;

/**
 * Created by duong on 07/04/2016.
 */
public class Notifications {

    Context context;
    private String title, constents;
    public Notifications(){}
    Class targetClass;
    public Notifications(Context context,Class targetClass, String title, String constents){
        this.title = title;
        this.constents = constents;
        this.context = context;
        this.targetClass = targetClass;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void addNotification(){
        NotificationCompat.Builder mBuider =
                new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(constents);

        Intent goMain = new Intent(context, targetClass);
        Bundle data = new Bundle();
        data.putInt("position", 123);
        goMain.putExtra("user_position", data);

        TaskStackBuilder stackBuidle = TaskStackBuilder.create(context);
        stackBuidle.addParentStack(targetClass);
        stackBuidle.addNextIntent(goMain);

        PendingIntent resultPedingIntent =
                stackBuidle.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        //setAction
        long [] parent = {500, 500, 500, 1000};
        Uri mSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuider.setVibrate(parent);
        mBuider.setContentIntent(resultPedingIntent);
        mBuider.setSound(mSound);
        mBuider.setAutoCancel(true);


        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuider.build());

    }


}
