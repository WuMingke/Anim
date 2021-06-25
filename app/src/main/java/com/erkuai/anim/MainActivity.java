package com.erkuai.anim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private ViewGroup notice_1, notice_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout root = findViewById(R.id.root);

        notice_1 = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.notice, null, false);
        notice_2 = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.notice, null, false);
        notice_1.setVisibility(View.GONE);
        notice_2.setVisibility(View.GONE);
        root.addView(notice_1);
        root.addView(notice_2);
    }

    public void notice1(View view) {
        doInAnim(notice_1);
    }

    private void doInAnim(View view) {
        view.setVisibility(View.VISIBLE);
        // 1、notice_1 进场放大
        PropertyValuesHolder inAlpha = PropertyValuesHolder.ofFloat("alpha", 0, 1);

        PropertyValuesHolder inScaleX = PropertyValuesHolder.ofFloat("scaleX", 0.75f, 1);

        PropertyValuesHolder inScaleY = PropertyValuesHolder.ofFloat("scaleY", 0.75f, 1);

        PropertyValuesHolder inTranslationY = PropertyValuesHolder.ofFloat("translationY", -100, 0); // height 要计算

        ObjectAnimator inAnim = ObjectAnimator.ofPropertyValuesHolder(view, inAlpha, inScaleX, inScaleY, inTranslationY);
        inAnim.setDuration(300);

        inAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 2、notice_1 进场平移
                ObjectAnimator inDownY = ObjectAnimator.ofFloat(view, "translationY", 0, 50);
                inDownY.setDuration(200);
                inDownY.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        inAnim.start();
    }

    private float firstTranslationY = 0;

    public void notice2(View view) {
        // 3、notice_1 向下隐藏
        PropertyValuesHolder outAlpha = PropertyValuesHolder.ofFloat("alpha", 1, 0.5f);

        PropertyValuesHolder outScaleX = PropertyValuesHolder.ofFloat("scaleX", 1, 0.8f);

        if (firstTranslationY == 0) {
            firstTranslationY = notice_1.getTranslationY();
        }

        PropertyValuesHolder outTranslationY = PropertyValuesHolder.ofFloat("translationY",
                firstTranslationY, firstTranslationY + 50); // height 要计算

        ObjectAnimator outAnim = ObjectAnimator.ofPropertyValuesHolder(notice_1, outAlpha, outScaleX, outTranslationY);
        outAnim.setDuration(300);
        outAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        outAnim.start();

        // 4、notice_2 出现
        doInAnim(notice_2);

    }

}