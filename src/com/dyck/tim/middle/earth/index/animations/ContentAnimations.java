package com.dyck.tim.middle.earth.index.animations;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dyck.tim.middle.earth.index.R;

/**
 * Animations for the ListView containing the content.
 */
public class ContentAnimations implements Animations {

    Activity activity;

    public ContentAnimations(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void homeToContent(int duration) {
        LinearLayout titleLayout = (LinearLayout) activity.findViewById(R.id.titleLayout);
        TextView title = (TextView) activity.findViewById(R.id.title);

        final EditText searchBox = (EditText) activity.findViewById(R.id.search);
        final HorizontalScrollView categoriesView = (HorizontalScrollView)
                activity.findViewById(R.id.categoriesView);
        final ListView contentList = (ListView) activity.findViewById(R.id.contentList);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) titleLayout.getLayoutParams();
        int offset = searchBox.getBottom() - title.getBottom() - categoriesView.getTop();

        TranslateAnimation contentAnim = new TranslateAnimation(0f, 0f, 0f, -500);
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
                params.topMargin = searchBox.getHeight();
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
