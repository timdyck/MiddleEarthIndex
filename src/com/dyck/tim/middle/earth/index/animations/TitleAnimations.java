package com.dyck.tim.middle.earth.index.animations;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dyck.tim.middle.earth.index.R;
import com.dyck.tim.middle.earth.index.activities.Transition;

/**
 * Animations for the title card.
 */
public class TitleAnimations implements Transition.Animations {

    Activity activity;

    public TitleAnimations(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void homeToContent(int duration) {
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

    @Override
    public void contentToData(int duration) {

    }

    @Override
    public void contentToHome(int duration) {
        final LinearLayout titleLayout = (LinearLayout) activity.findViewById(R.id.titleLayout);
        final TextView title = (TextView) activity.findViewById(R.id.title);

        TranslateAnimation titleAnim = new TranslateAnimation(0f, 0f, 0f, title.getBottom());
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
                params.topMargin = 0;
                titleLayout.setLayoutParams(params);
            }
        });

        titleLayout.startAnimation(titleAnim);
    }

    @Override
    public void dataToContent(int duration) {

    }

    @Override
    public void dataToHome(int duration) {
        dataToContent(duration);
        contentToHome(duration);
    }

}
