package duong.tieu.vdmproject.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import duong.tieu.vdmproject.activities.SearchActivity;
import duong.tieu.vdmproject.models.Models;
import duong.tieu.vdmproject.notifications.NotificationListener;
import duong.tieu.vdmproject.notifications.Notifications;

/**
 * Created by Elitebook on 4/9/2016.
 */
public class BroadcastNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        new Notifications(context, SearchActivity.class, "bật service", " abx").addNotification();
        Bundle bundle = intent.getBundleExtra(Models.PACKAGE);
        String json = bundle.getString(Models.DATA);
        Toast.makeText(context, json, Toast.LENGTH_SHORT).show();
        new NotificationListener().analyze(json);
    }


}
