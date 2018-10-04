package com.example.user.finalprojectapp1.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.example.user.finalprojectapp1.API.AppPreferences;
import com.example.user.finalprojectapp1.API.MyAsynctask;
import com.example.user.finalprojectapp1.API.RequestBody;
import com.example.user.finalprojectapp1.API.RequestModel;
import com.example.user.finalprojectapp1.Background.UpdateStatusService;
import com.example.user.finalprojectapp1.MainActivity;
import com.example.user.finalprojectapp1.Model.SessionModel;
import com.example.user.finalprojectapp1.Model.UserModel;
import com.example.user.finalprojectapp1.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = findViewById(R.id.email);

        mPasswordView = findViewById(R.id.pass);
        /*mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {

                    return true;
                }
                return false;
            }
        });*/
        Log.d("chiu","1");
        Button SignInButton = (Button) findViewById(R.id.button_login);

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postLogin(mEmailView.getText().toString(),mPasswordView.getText().toString());
                //  Log.d("chiu1",AppPreferences.getInstance(LoginActivity.this).getSession().getMessage());


            }
        });


    }

    private void postLogin(String email, String password) {
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setPassword(password);
        RequestBody body = new RequestBody();
        body.setUser(userModel);
        Log.d("chiu","2");
        RequestModel requestModel = new RequestModel();
        requestModel.setRequest_method(RequestModel.POST);
        requestModel.setApi_url(requestModel.postLogin());
        requestModel.setBody_request(body);
        Log.d("chiu","4");
        MyAsynctask asynctask = new MyAsynctask();
        asynctask.setContext(LoginActivity.this);
        asynctask.setProcessing_listener(new MyAsynctask.MyAsyncTaskListener() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(String result) {
                if (result!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        Log.d("Login result",result);
                        SessionModel smartQueueModel = new Gson().fromJson(result, new TypeToken<SessionModel>() {}.getType());
                        AppPreferences.getInstance(LoginActivity.this).setSession(smartQueueModel);

                        //SessionModel sessionModel = AppPreferences.getInstance(LoginActivity.this).getSession();
                        String json3 = new Gson().toJson(smartQueueModel);
                        Log.d("haha",smartQueueModel.getMessage());


                        if(!jsonObject.isNull("message")){
                            Log.d("asd","dmm");

                            Log.d("asd","dmm1");

                            if(AppPreferences.getInstance(LoginActivity.this).getChecked()){
                                Intent intent2 = new Intent(LoginActivity.this,Test.class);
                                Intent intent1 = new Intent(LoginActivity.this,UpdateStatusService.class);
                                startService(intent1);
                                startActivity(intent2);
                            } else{
                                Intent intent = new Intent(LoginActivity.this,Setting.class);
                                startActivity(intent);
                            }



                        }else{
                            Log.d("chiu","nho");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        });
        asynctask.execute(requestModel);
    }

}
