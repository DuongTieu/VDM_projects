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

import com.google.gson.Gson;

import duong.tieu.vdmproject.R;

/**
 * Created by duong on 07/04/2016.
 */
public class Notifications {

    Class targetClass;
    private Context mContext;
    private String mTitle, mContents, mJson;
    private int mType, mType2;

    public Notifications() {
    }

    public Notifications(Context context, Class targetClass, String title, String constents) {
        this.mTitle = title;
        this.mContents = constents;
        this.mContext = context;
        this.targetClass = targetClass;
    }

    public Notifications(Context mContext, Class targetClass, String mTitle, String mContents, String mJson, int type) {
        this.mContext = mContext;
        this.mTitle = mTitle;
        this.mContents = mContents;
        this.mJson = mJson;
        this.targetClass = targetClass;
        this.mType = type;
    }
    public Notifications(Context mContext, Class targetClass, String mTitle, String mContents, String mJson, int type, int type2) {
        this.mContext = mContext;
        this.mTitle = mTitle;
        this.mContents = mContents;
        this.mJson = mJson;
        this.targetClass = targetClass;
        this.mType = type;
        this.mType2 = type2;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void addNotification() {
        NotificationCompat.Builder mBuider =
                new NotificationCompat.Builder(mContext)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(mTitle)
                        .setContentText(mContents);

        Intent goMain = new Intent(mContext, targetClass);
        //kiểm tra xem có phải là gửi tin nhắn hay không
        checkType(goMain);

        TaskStackBuilder stackBuidle = TaskStackBuilder.create(mContext);
        stackBuidle.addParentStack(targetClass);
        stackBuidle.addNextIntent(goMain);

        PendingIntent resultPedingIntent =
                stackBuidle.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        //setAction
        long[] parent = {500, 500, 500, 1000};
        Uri mSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuider.setVibrate(parent);
        mBuider.setContentIntent(resultPedingIntent);
        mBuider.setSound(mSound);
        mBuider.setAutoCancel(true);


        NotificationManager notificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuider.build());

    }

    private void checkType(Intent goMain) {
        Bundle data = new Bundle();
        Gson gson = new Gson();
        if(mType == 1){
            //tin nhắn
        }else if(mType == 2 && mType2 == 1){
            //dự án liên quan - 1 dự án mới
        }else if(mType == 2 && mType2 == 2){
            //dự án liên quan - thích dự án đó
        }else  if(mType == 2 && mType2 == 3){
            //dự án liên quan- comment mới
        }else  if(mType == 2 && mType2 == 4){
            //dự án liên quan - thêm người quan tâm
        }else if(mType == 3 && mType2 == 1){
            //dự án quan tâm  - 1 dự án mới
        }else if(mType == 3 && mType2 == 2){
            //dự án quan tâm-  thích dự án đó
        }else if(mType == 3 && mType2 == 3){
            //dự án quan tâm - comment mới
        }else if(mType == 3 && mType2 == 4){
            //dự án quan tâm - có thêm người quan tâm
        }else if(mType == 4){
            //hỗ trợ
        }






    }
/*

    private void checkType(Intent goMain) {
        Bundle data = new Bundle();
        Gson gson = new Gson();
        //tin nhắn
        if (mType == 1) {
            DMessages item =
                    gson.fromJson(mJson, DMessages.class);

            data.putString("id", item.getId());
            data.putString("from", item.getFrom());
            data.putString("to", item.getTo());
            data.putString("contents", item.getContent());
            data.putString("cdate", item.getCdate());
            data.putString("isrun", item.getIsrun());
            goMain.putExtra("message", data);

        }   else if (mType == 2) {

            DGetProject item =
                    gson.fromJson(mJson, DGetProject.class);

            Log.i("Tag", item.getC_comment()+ "   ");
            data.putString("id", item.getId());
            data.putString("car_id", item.getCar_id());
            data.putString("title", item.getTitle());
            data.putString("intro", item.getIntro());
            data.putString("content", item.getContent());
            data.putString("like", item.getLike());
            data.putString("unlike", item.getUnlike());
            data.putString("date", item.getCdate());
            goMain.putExtra("news", data);
        }
    }

*/

}
