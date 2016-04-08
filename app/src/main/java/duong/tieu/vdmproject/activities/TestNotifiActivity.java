package duong.tieu.vdmproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.models.DGetProject;
import duong.tieu.vdmproject.models.DMessages;

public class TestNotifiActivity extends AppCompatActivity {


    private static final String PUBLIC_KEY = "pub-c-470b0c62-1d29-4905-ad3d-10e41ecae909";
    private static final String SUBSCRIBE_KEY = "sub-c-047ca2dc-fbc7-11e5-861b-02ee2ddab7fe";
    private static final String SECRET_KEY = "sec-c-NmIyOTA3NTMtYTY1Yi00Nzc2LWI1MmItOGQ2MjA0OGNkZjEy";
    private String mUser;
    private String mJson;
    private String mPubChannel;

    private Pubnub mPubnub = new Pubnub(
            PUBLIC_KEY,  // PUBLISH_KEY   (Optional, supply "" to disable)
            SUBSCRIBE_KEY,  // SUBSCRIBE_KEY (Required)
            SECRET_KEY,      // SECRET_KEY    (Optional, supply "" to disable)
            "",      // CIPHER_KEY    (Optional, supply "" to disable)
            false    // SSL_ON?
    );

    private Callback mCallback;

    private DMessages messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notifi);
        mPubChannel = "vsi_group_chanel_notification";

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
                TestNotifiActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setList(message.toString());
                    }
                });

            }

            @Override
            public void errorCallback(String channel, PubnubError error) {
                Log.i("errorCallback", "SUBSCRIBE : ERROR on channel " + channel
                        + " : " + error.toString());
            }
        };
        try {
            mPubnub.subscribe(mPubChannel, mCallback);
        } catch (PubnubException e) {
            e.printStackTrace();
        }


        addEvents();

    }

    private String createMessages() {

        DMessages messages = new DMessages();
        messages.setId("1");
        messages.setFrom("admin");
        messages.setTo("nhahv0902");
        messages.setCdate("12/22/1995");
        messages.setContent("hoang nha");
        messages.setIsrun("1");

        String json = new Gson().toJson(messages);
        return json;
    }

    private String createProjectItem() {
        DGetProject dGetProject = new DGetProject();
        dGetProject.setId("1");
        dGetProject.setContent("slkfsakl;fjsaf");
        dGetProject.setCode("sjkfskajfaskl");
        dGetProject.setFrom_date("safklnslakfmnskl;a");
        String json = new Gson().toJson(dGetProject);
        return json;
    }

    private void addEvents() {
        Button button = (Button) findViewById(R.id.btNotification);
        final String json = createMessages();
        if (button != null)
            button.setOnClickListener(new Events(json));
    }

    public void setList(String list) {
        this.mJson = list;
        messages = new Gson().fromJson(mJson, DMessages.class);
    }

    private class Events implements View.OnClickListener {

        public String string;

        public Events(String string) {
            this.string = string;
        }

        @Override
        public void onClick(View v) {
            mPubnub.publish(mPubChannel, string, new Callback() {
                @Override
                public void successCallback(String channel, Object message) {
                    super.successCallback(channel, message);
                }

                @Override
                public void errorCallback(String channel, PubnubError error) {
                    super.errorCallback(channel, error);
                }
            });

            Log.i("Tag", mJson);
        }
    }
}
