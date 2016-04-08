package duong.tieu.vdmproject.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_email)
    EditText _userText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


//        btn_click
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        //goto signup layout
        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();



        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 500);
    }




    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);

        String email = _userText.getText().toString();
        String password = _passwordText.getText().toString();

        Login login = (Login) new Login(new Login.AsyncResponse() {
            @Override
            public void processFinish(String output) {
//                Boolean.parseBoolean(output)
                if (true) {
                    Intent goMain = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(goMain);
                    finish();
                } else {
                    onLoginFailed();
                    return;
                }

            }
        }).execute(email, password);






    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }


    private static class Login extends AsyncTask<String, Void, String> {

        public AsyncResponse delegate = null;

        public Login(AsyncResponse delegate) {
            this.delegate = delegate;
        }

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
            Boolean isLogin = Boolean.parseBoolean(iLogin.getStatus());
            delegate.processFinish(iLogin.getStatus());
            Log.i("Tag", isLogin + "");
        }


        public interface AsyncResponse {
            void processFinish(String output);
        }
    }
}
