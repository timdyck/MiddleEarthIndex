package com.dyck.tim.middle.earth.index.animations;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dyck.tim.middle.earth.index.R;
import com.dyck.tim.middle.earth.index.activities.Transition;

/**
 * Animations for the HorizontalScrollView containing the categories.
 */
public class CategoryAnimations implements Transition.Animations {

    Activity activity;

    public CategoryAnimations(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void homeToContent(int duration) {
        LinearLayout titleLayout = (LinearLayout) activity.findViewById(R.id.titleLayout);
        TextView title = (TextView) activity.findViewById(R.id.title);

        final EditText searchBox = (EditText) activity.findViewById(R.id.search);
        final HorizontalScrollView categoriesView = (HorizontalScrollView)
                activity.findViewById(R.id.categoriesView);

        int offset = searchBox.getBottom() - title.getBottom() - categoriesView.getTop();

        TranslateAnimation categoryAnim = new TranslateAnimation(0f, 0f, 0f, offset);
        categoryAnim.setDuration(duration);

        categoryAnim.setAnimationListener(new TranslateAnimation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                        categoriesView.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
                params.topMargin = searchBox.getHeight();
                categoriesView.setLayoutParams(params);
            }
        });

        categoriesView.startAnimation(categoryAnim);
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
