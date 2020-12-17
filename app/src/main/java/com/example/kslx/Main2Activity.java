package com.example.kslx;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kslx.view.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {
    private ImageView dh_iv;
    private TextView dh_tv;
    private String str[]={"1","2","3","4","5"};
    private int num = 4;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        timer = new Timer();

//倒计时
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                num--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dh_tv.setText("倒计时："+str[num]);
                    }
                });
                if(num==0){
                    //关闭定时器
                    timer.cancel();
                    //跳转页面
                    try{
                        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                        startActivity(intent);
                    }catch (ActivityNotFoundException a){
                        a.getMessage();
                    }
                }
            }
        },1000,1000);
        initData();
    }

    private void initView() {
        dh_iv = (ImageView) findViewById(R.id.dh_iv);
        dh_tv = (TextView) findViewById(R.id.dh_tv);

    }
    private void initData() {
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(dh_iv, View.TRANSLATION_Y, 0F, 200F);
        ObjectAnimator oa4 = ObjectAnimator.ofFloat(dh_iv, View.TRANSLATION_X, 0F, 200F);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(dh_iv, View.SCALE_X, 0F, 4F,1F);
        ObjectAnimator oa5 = ObjectAnimator.ofFloat(dh_iv, View.ROTATION_X, 1, 90,180,360);
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(dh_iv, View.ALPHA, 1, 0,1);

        //该对象用于添加联合动画
        AnimatorSet animatorSet = new AnimatorSet();
        //一起执行
        //animatorSet.playTogether(oa1,oa2,oa3,oa5,oa4);
        //一个接一个的执行
        animatorSet.playSequentially(oa1,oa2,oa3,oa5,oa4);
        //开始组合动画
        animatorSet.setDuration(2500).start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                //动画开始

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //动画结束
                //跳转

            }

            @Override
            public void onAnimationCancel(Animator animator) {
                //动画取消
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                //动画重复
            }
        });
    }
}
