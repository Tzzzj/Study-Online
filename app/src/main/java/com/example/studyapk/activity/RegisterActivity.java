package com.example.studyapk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.bean.UserData;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //申明变量
    private Button back;
    private EditText userName;
    private EditText passWord;
    private EditText againPassWord;
    private EditText personName;
    private EditText IDCard;
    private RadioGroup rd_userSex;
    private EditText userTelphone;
    private CheckBox cb_agree;
    private TextView tv_service;
    private Button registerbutton;
    private static boolean sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //初始化组件
        initView();
        setupEvent();
    }

    /**
     * 控件初始化
     */
    private void initView(){
        back = findViewById(R.id.register_back);
        userName = findViewById(R.id.register_username);
        passWord = findViewById(R.id.register_password);
        againPassWord = findViewById(R.id.register_againpassword);
        personName = findViewById(R.id.register_personname);
        IDCard = findViewById(R.id.register_IDcard);
        rd_userSex = findViewById(R.id.register_rd_sex);
        userTelphone = findViewById(R.id.register_usertelphone);
        cb_agree = findViewById(R.id.register_cb_agree);
        tv_service = findViewById(R.id.register_tv_service);
        registerbutton = findViewById(R.id.register_registerbutton);

        //给sex设置初始值
        sex = true;
    }

    /**
     * 设置监听事件
     */
    private void setupEvent(){
        back.setOnClickListener(this);
        registerbutton.setOnClickListener(this);
        rd_userSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //获取选中的控件Id
                int radioButtonId = group.getCheckedRadioButtonId();
                //通过Id实例化控件
                RadioButton rb = findViewById(radioButtonId);
                //获取rb中的text值
                if(rb.getText().toString().trim().equals("男")){
                    sex = true;
                }else if(rb.getText().toString().trim().equals("女")){
                    sex = false;
                }
            }
        });
        cb_agree.setOnClickListener(this);
        tv_service.setOnClickListener(this);
    }

    /**
     * 注册
     */
    public void register(){
        if(checkInfo()){
            //定义局部变量接收获取ed中的信息
            String userName = this.userName.getText().toString().trim().toLowerCase();
            String passWord = this.passWord.getText().toString().trim();
            String personName = this.personName.getText().toString().trim();
            String IDCard = this.IDCard.getText().toString().trim();
            boolean userSex = sex;
            String userTelphone = this.userTelphone.getText().toString();
            //通过Bomb来储存信息
            UserData userData = new UserData(personName,IDCard,userSex,userTelphone);
            //BmobUser当中的函数


        }
    }

    /**
     * 验证信息
     */
    @SuppressLint("DefaultLocale")   //Lint是一个静态检查器
    public boolean checkInfo(){
        //定义局部变量接收获取ed中的信息
        String userName = this.userName.getText().toString().trim();
        String passWord = this.passWord.getText().toString().trim();
        String againPassword = this.againPassWord.getText().toString().trim();
        String personName = this.personName.getText().toString().trim();
        String IDCard = this.IDCard.getText().toString().trim().toUpperCase();
        String userTelphone = this.userTelphone.getText().toString();

        //判断
        if(userName.equals("")){
            //userName不为空
            Toast.makeText(this, "用户名不得为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if(userName.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){
            //userName没有特殊符号
            Toast.makeText(this, "无效用户名", Toast.LENGTH_SHORT).show();
            return false;
        }else if(passWord.equals("")){
            //passWord不为空
            Toast.makeText(this, "密码不得为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if(passWord.length() < 6 || passWord.length() > 17){
            //passWord太短或太长
            Toast.makeText(this, "密码应该6-17位之间", Toast.LENGTH_SHORT).show();
            return false;
        }else if(againPassword.equals("")){
            //againPassWord不为空
            Toast.makeText(this, "再次输入密码不得为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!againPassword.equals(passWord)){
            //判断两次输入密码是否一样
            Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }else if(personName.equals("")){
            //personName不为空
            Toast.makeText(this, "个人姓名不得为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if(IDCard.equals("")){
            //IDCard不为空
            Toast.makeText(this, "IDCard不得为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if(IDCard.length() != 18){
            //IDCard无效
            Toast.makeText(this, "无效IDCard", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!IDCard.matches("[0-9]{17}[0-9,X]")){
            //IDCard无效
            Toast.makeText(this, "无效IDCard", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!judgeIDCard6()){
            //IDCard无效
            Toast.makeText(this, "无效IDCard", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!judgeIDCard8()){
            //IDCard无效
            Toast.makeText(this, "无效IDCard", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!judgeIDCard4()){
            //IDCard无效
            Toast.makeText(this, "无效IDCard", Toast.LENGTH_SHORT).show();
            return false;
        }else if(userTelphone.length() != 11 && !judgeTelphone()){
            //Telphone无效
            Toast.makeText(this, "无效Telphone", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!judgeTelphone()){
            //Telphone无效
            Toast.makeText(this, "无效Telphone", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!cb_agree.isChecked()){
            //没有勾选协议
            Toast.makeText(this, "请勾选同意协议", Toast.LENGTH_SHORT).show();
            return false;
        }
        //通过判断则返回true
        return true;
    }

    /**
     * 验证身份证前6位
     */
    @SuppressLint("DefaultLocale")   //Lint是一个静态检查器
    public boolean judgeIDCard6(){
        //通过局部变量获取IDCard
        String IDCard = this.IDCard.getText().toString().trim().toUpperCase();
        //获取身份证前两位
        String cardTwo = IDCard.substring(0,2);
        String[] num = new String[]{ "11", "12", "13", "14", "15", "21", "22",
                "23", "31", "32", "33", "34", "35", "36", "37", "41", "42",
                "43", "44", "45", "46", "50", "51", "52", "53", "54", "61",
                "62", "63", "64", "65", "71", "81", "82", "91" };
        //通过循环来判断前两位是否符合
        for(int i =0;i<num.length;i++){
            if(cardTwo.equals(num[i])){
                return true;
            }
        }
        //从循环出来则返回false
        return false;
    }

    /**
     * 验证身份证中8位
     */
    @SuppressLint("DefaultLocale")   //Lint是一个静态检查器
    public boolean judgeIDCard8(){
        //通过局部变量获取IDCard
        String IDCard = this.IDCard.getText().toString().trim().toUpperCase();
        //找到“年”的位置   附上强制转换
        int year = Integer.parseInt(IDCard.substring(6,10));
        //找到月”的位置   附上强制转换
        int month = Integer.parseInt(IDCard.substring(10,12));
        //找到“日”的位置   附上强制转换
        int day = Integer.parseInt(IDCard.substring(12,14));
        //不能够超过今年
        if(year > 2022){
            return false;
        }
        //月份不大于12
        if(month > 12){
            return false;
        }
        //将天数分为31、30、29、28
        if(month == 1 || month == 3 || month == 5 ||month == 7
        || month == 8 || month == 10 || month == 12)
            if(day > 31){
                return  false;
            }
        if(month == 4 || month == 6 || month == 9 || month == 11)
            if(day > 30){
                return false;
            }
        if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0 &&(month == 2))
            if(day > 29){
                return false;
            }
        if(!(year % 4 == 0 &&year % 100 != 0 || year % 400 == 0)&&(month ==2 ))
            if(day > 28 ){
                return false;
            }
        return true;
    }

    /**
     * 验证身份证后4位
     */
    @SuppressLint("DefaultLocale")   //Lint是一个静态检查器
    public boolean judgeIDCard4(){
        String userIdcard = this.IDCard.getText().toString().trim()
                .toUpperCase();
        char num = userIdcard.charAt(17);
        char[] id = userIdcard.toCharArray();
        int[] idnum = new int[id.length];
        for (int i = 0; i < 17; i++)
            idnum[i] = Integer.parseInt(String.valueOf(id[i]));
        int[] ratio = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
                8, 4, 2 };
        int sum = 0;
        for (int i = 0; i < ratio.length; i++)
            sum += ratio[i] * idnum[i];
        int remainder = sum % 11;
        int[] rem = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        char[] last = new char[] { '1', '0', 'X', '9', '8', '7', '6', '5', '4',
                '3', '2' };
        char lastnum = 'A';
        for (int i = 0; i < rem.length; i++)
            if (rem[i] == remainder)
                lastnum = last[i];
        if (lastnum == num)
            return true;
        else
            return false;
    }

    /**
     * 验证电话号码
     */
    @SuppressLint("DefaultLocale")   //Lint是一个静态检查器
    public boolean judgeTelphone(){
        //通过局部变量获取手机号
        String userTelpnhone = this.userTelphone.getText().toString().trim();
        //手机开头
        String[] nk = new String[] { "130", "131", "132", "133", "134", "135",
                "136", "137", "138", "139", "150", "151", "152", "153", "154",
                "155", "156", "157", "158", "159", "170", "180", "181", "182",
                "183", "184", "185", "186", "187", "188", "189" };
        //判断手机号长度和标准
        if(userTelpnhone.length() == 11){
            String userTelpnhone_3 = userTelpnhone.substring(0,3);
            //通过for循环来对手机号前三位进行规范
            int i;
            for(i = 0 ;i < nk.length;i++){
                if(userTelpnhone_3.equals(nk[i])){
                    break;
                }
            }
            //加入没有匹配到也会跳出循环,所以对 i 进行一层判断
            if(i < nk.length){
                return true;
            }else{
                return false;
            }
        } else{
            return false;
        }
    }

    /**
     * 按键触发
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_back:
                //Intent intent = new Intent();
                //intent.setClass(RegisterActivity.this,MainActivity.class);
                //startActivity(intent);
                Toast.makeText(this, "返回到主界面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.register_registerbutton:
                register();
                Toast.makeText(this, "信息符合要求", Toast.LENGTH_SHORT).show();
                break;
            case R.id.register_tv_service:
                Toast.makeText(this, "《服务》", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
