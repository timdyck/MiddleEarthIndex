package com.dyck.tim.middle.earth.index.animations;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dyck.tim.middle.earth.index.R;

public class TitleAnimations {

    Activity activity;

    public TitleAnimations(Activity activity) {
        this.activity = activity;
    }

    public void slideUp(int duration){
        final LinearLayout titleLayout = (LinearLayout) activity.findViewById(R.id.titleLayout);
        final TextView title = (TextView) activity.findViewById(R.id.title);

        TranslateAnimation titleAnim = new TranslateAnimation(0f, 0f, 0f, -title.getBottom());
        titleAnim.setDuration(duration);
        titleAnim.setAnimationListener(new TranslateAnimation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) titleLayout.getLayoutParams();
                params.topMargin += -title.getBottom();
                titleLayout.setLayoutParams(params);
            }
        });

        titleLayout.startAnimation(titleAnim);
    }
}
