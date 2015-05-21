package com.dyck.tim.middle.earth.index.animations;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.dyck.tim.middle.earth.index.R;
import com.dyck.tim.middle.earth.index.activities.Transition;

/**
 * Animations for the ListView containing the content.
 */
public class ContentAnimations implements Transition.Animations {

    Activity activity;

    public ContentAnimations(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void homeToContent(int duration) {
        final EditText searchBox = (EditText) activity.findViewById(R.id.search);
        final HorizontalScrollView categoriesLayout = (HorizontalScrollView)
                activity.findViewById(R.id.categoriesView);
        final ListView contentList = (ListView) activity.findViewById(R.id.contentList);

        final int offset = searchBox.getHeight() + categoriesLayout.getHeight();

        TranslateAnimation contentAnim = new TranslateAnimation(0f, 0f, contentList.getHeight(), offset);
        contentAnim.setDuration(duration);

        contentAnim.setAnimationListener(new TranslateAnimation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                contentList.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                        contentList.getLayoutParams();
                params.topMargin = offset;
                contentList.setLayoutParams(params);
            }
        });

        contentList.startAnimation(contentAnim);
    }

    @Override
    public void contentToData(int duration) {

    }

    @Override
    public void contentToHome(int duration) {
        final EditText searchBox = (EditText) activity.findViewById(R.id.search);
        final HorizontalScrollView categoriesLayout = (HorizontalScrollView)
                activity.findViewById(R.id.categoriesView);
        final ListView contentList = (ListView) activity.findViewById(R.id.contentList);

        final int offset = searchBox.getHeight() + categoriesLayout.getHeight();

        TranslateAnimation contentAnim = new TranslateAnimation(0f, 0f, 0f, contentList
                .getHeight());
        contentAnim.setDuration(duration);

        contentAnim.setAnimationListener(new TranslateAnimation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                        contentList.getLayoutParams();
                params.topMargin = 0;
                contentList.setLayoutParams(params);
                contentList.setVisibility(View.INVISIBLE);
            }
        });

        contentList.startAnimation(contentAnim);
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
