package duong.tieu.vdmproject.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.models.ILogin;
import duong.tieu.vdmproject.models.Models;
import duong.tieu.vdmproject.services.MyServices;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.edUsername)
    EditText edUsername;
    @Bind(R.id.edPassword)
    EditText edPassword;
    @Bind(R.id.btLogin)
    Button btLogin;
    @Bind(R.id.tvSignUp)
    TextView tvSignUp;

    private ProgressDialog mDialog;
    private boolean isLogin;

    public static String mUsername;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        addEvents();
    }

    private void addEvents() {
        btLogin.setOnClickListener(new Events());
        tvSignUp.setOnClickListener(new Events());
    }

    private class Events implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btLogin:
                    onClickLogin();
                    break;
                case R.id.tvSignUp:
                    onClickSignUp();
                    break;
            }
        }

        private void onClickSignUp() {
            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(intent);
        }
    }

    public void onClickLogin() {

        btLogin.setEnabled(false);
        showProgressDialog();
        new Thread() {
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(500);
                onLoginSuccess();
                hideProgressDialog();
            }
        }.start();
    }

    private void showProgressDialog() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            mDialog.setTitle("Loading");
            mDialog.setMessage("Please waite minute...");
        }
        mDialog.show();
    }

    private void hideProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {

        String email = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        if (email.equals("")) {
            return;
        }
        if (password.equals("")) {
            return;
        }
        new Login().execute(email, password);
    }

    private class Login extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... data) {

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(Models.USER_NAME, data[0]));
            params.add(new BasicNameValuePair(Models.PASS_WORD, data[1]));

            MyServices myServices = new MyServices();
            String string = myServices.post(Models.URL + Models.LOGIN, params);
            publishProgress();
            return string;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            s = s.substring(s.indexOf("{"));
            ILogin iLogin = new Gson().fromJson(s, ILogin.class);
//            mUsername = iLogin.getData().getUsername();
            mUsername = "admin";
            isLogin = Boolean.parseBoolean(iLogin.getStatus());
            Log.i("Tag", mUsername);
            isLogin = true;
            if (isLogin) {
                Intent goMain = new Intent(getBaseContext(), MainActivity.class);
                startActivity(goMain);
                finish();
            }
        }
    }
}
