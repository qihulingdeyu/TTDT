package com.qing.ttdt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.qing.ttdt.R;

public class UserLoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        
        ImageButton userlogin2homepage = (ImageButton) findViewById(R.id.userlogin2homepage);
        userlogin2homepage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivity(HomePageActivity.class, null, true);
            }
        });
        
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email_inp = (EditText) findViewById(R.id.email_inp);
                String email = email_inp.getText().toString();
                EditText password_inp = (EditText) findViewById(R.id.password_inp);
                String password = password_inp.getText().toString();

                showToast("邮箱："+email+"\n密码："+password);
            }
        });
        
    }

}
