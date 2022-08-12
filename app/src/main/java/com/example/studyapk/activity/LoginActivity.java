package com.example.studyapk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //打印日志
    final static String TAG = "Login";
    //定义常量
    private EditText username;
    private EditText password;
    private CheckBox cbRemPassword;
    private CheckBox cbAutoLogin;
    private ImageButton btnLogin;
    private TextView forgetPassword;
    private TextView register;
    private SharedPreferences sp;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp =this.getSharedPreferences("userInfo",MODE_PRIVATE);
        //初始化控件
        initView();
        //设置监听事件
        setupEvent();

        //判断记住密码复选框的状态
        if(sp.getBoolean("ISCHECK",false)){
            //设置默认是记住密码
            cbRemPassword.setChecked(true);
            username.setText(sp.getString("USER_NAME",""));
            username.setText(sp.getString("PASSWORD",""));

            //有记住密码才有自动登录 记住密码为自动登录的充分不必要条件
            if(sp.getBoolean("AUTO_IDCHECK",false)){
                //默认自动登录状态
                cbAutoLogin.setChecked(true);

            }
        }




    }

    /**
     *初始化控件
     */
    private void initView(){
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.register_password);
        cbRemPassword = findViewById(R.id.login_cbremPassword);
        cbAutoLogin = findViewById(R.id.login_cbautoLogin);
        btnLogin = findViewById(R.id.login_btnlogin);
        forgetPassword = findViewById(R.id.login_forgetpassword);
        register = findViewById(R.id.login_register);
    }

    /**
     *设置监听事件
     */
    private void setupEvent(){
        btnLogin.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
        register.setOnClickListener(this);
        //监听记住密码的复选框
        cbRemPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //记住密码被选中
                if(cbRemPassword.isChecked()){
                    sp.edit().putBoolean("ISCHECK",true).commit();
                }else {
                    sp.edit().putBoolean("ISCHECK",false).commit();
                }
            }
        });
        //监听自动登录的复选框
        cbAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //自动登录被选中
                if(cbAutoLogin.isChecked()){
                    System.out.println("自动登录被选中");
                    cbAutoLogin.setChecked(true);
                    sp.edit().putBoolean("AUTO_ISCHECK",true).commit();
                }else{
                    System.out.println("自动登录未选中");
                    sp.edit().putBoolean("AUTO_ISCHECK",false).commit();
                }
            }
        });
    }

    /**
     *登录
     */
    public void login(){
        if(isUserNameAndPasswordValid()){
            //定义常量接收ed框值
            final String username = this.username.getText().toString().trim();
            final String password = this.password.getText().toString().trim();
            //
            //
            //
            //
            //
        }
    }

    /**
     *用户名和密码的有效性
     */
    public boolean isUserNameAndPasswordValid(){
        if(username.getText().toString().trim().equals("")){
            Toast.makeText(this, "用户名不得为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if(password.getText().toString().trim().equals("")){
            Toast.makeText(this, "密码不得为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     *点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btnlogin:
                login();
                break;
            case R.id.login_register:
                Intent intent_register = new Intent();
                intent_register.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(intent_register);
                break;
            case R.id.login_forgetpassword:
                //Intent intent_forgetpassword = new Intent();
                //startActivity(intent_forgetpassword);
                Toast.makeText(this, "忘记密码", Toast.LENGTH_SHORT).show();
        }
    }
}
