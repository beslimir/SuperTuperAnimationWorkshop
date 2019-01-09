package com.hemlet.beslimir.animationsworkshop03;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, Transition.TransitionListener {

    ImageView imView4;
    RelativeLayout rootReveal;
    Scene scene1, scene2;
    ChangeBounds mySwapTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imView4 = findViewById(R.id.square_4);
        rootReveal = findViewById(R.id.reveal_root);

        scene1 = Scene.getSceneForLayout(rootReveal, R.layout.activity_main, this);
        scene2 = Scene.getSceneForLayout(rootReveal, R.layout.scene_2, this);

        mySwapTransition = new ChangeBounds();
        mySwapTransition.addListener(this);

        imView4.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            if (view.getId() == R.id.square_4) {
//                reveal4((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                startScene();
            }
        }
        return false;
    }



    private void reveal4(int rawX, int rawY) {
        float radius = (float) Math.hypot(rootReveal.getWidth(), rootReveal.getHeight());

        rootReveal.setVisibility(View.VISIBLE);
        rootReveal.setBackgroundColor(getResources().getColor(R.color.pink));

        Animator animator = ViewAnimationUtils.createCircularReveal(
                rootReveal, rawX, rawY, 0, radius);
        animator.setDuration(1000)
                .setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    private void startScene() {
        TransitionManager.go(scene2, mySwapTransition);
    }

    @Override
    public void onTransitionStart(Transition transition) {

    }

    @Override
    public void onTransitionEnd(Transition transition) {

    }

    @Override
    public void onTransitionCancel(Transition transition) {

    }

    @Override
    public void onTransitionPause(Transition transition) {

    }

    @Override
    public void onTransitionResume(Transition transition) {

    }
}
