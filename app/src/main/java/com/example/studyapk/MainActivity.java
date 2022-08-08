package com.example.studyapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studyapk.activity.LoginActivity;

public class MainActivity extends AppCompatActivity {

    //定义登录注册按钮
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //通过id属性找到button对象
        btnLogin = findViewById(R.id.btn_main_login);
        //添加点击监听对象
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转
                Intent in_main_login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(in_main_login);
            }
        });

    }
}