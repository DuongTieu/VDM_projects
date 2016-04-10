package duong.tieu.vdmproject.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import duong.tieu.vdmproject.models.Models;
import duong.tieu.vdmproject.notifications.NotificationListener;

public class MyService extends Service {

    private Pubnub mPubnub = new Pubnub(
            Models.PUBLIC_KEY,  // PUBLISH_KEY   (Optional, supply "" to disable)
            Models.SUBSCRIBE_KEY,  // SUBSCRIBE_KEY (Required)
            Models.SECRET_KEY,      // SECRET_KEY    (Optional, supply "" to disable)
            "",      // CIPHER_KEY    (Optional, supply "" to disable)
            false    // SSL_ON?
    );
    private Callback mCallback;

    public MyService( ) {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw null;
    }

    private void init(){
        mCallback = new Callback() {
            @Override
            public void connectCallback(String channel, Object message) {
                Log.i("connectCallbackRe", "SUBSCRIBE : CONNECT on channel:" + channel
                        + " : " + message.getClass() + " : "
                        + message.toString());
            }

            @Override
            public void disconnectCallback(String channel, Object message) {
                Log.i("disconnectCallback", "SUBSCRIBE : DISCONNECT on channel:" + channel
                        + " : " + message.getClass() + " : "
                        + message.toString());
            }

            public void reconnectCallback(String channel, Object message) {
                Log.i("disconnectCallback", "SUBSCRIBE : RECONNECT on channel:" + channel
                        + " : " + message.getClass() + " : "
                        + message.toString());
            }

            @Override
            public void successCallback(String channel, final Object message) {
                Log.i("successCallbackSend", "SUBSCRIBE : " + channel + " : "
                        + message.getClass() + " : " + message.toString());
                convertJson(message.toString());

            }

            @Override
            public void errorCallback(String channel, PubnubError error) {
                Log.i("errorCallback", "SUBSCRIBE : ERROR on channel " + channel
                        + " : " + error.toString());
            }
        };
        try {
            mPubnub.subscribe(Models.CHANNEL_NOTIFICATION, mCallback);
        } catch (PubnubException e) {
            e.printStackTrace();
        }

    }

    private void convertJson(String string) {
        Log.i("TagSevices", string);
       // Toast.makeText(MyService.this, string, Toast.LENGTH_SHORT).show();
         new NotificationListener(getBaseContext()).analyze(string);
    }

}
