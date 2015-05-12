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

public class CategoryAnimations {

    Activity activity;

    public CategoryAnimations(Activity activity) {
        this.activity = activity;
    }

    public void slideUp(int duration) {
        LinearLayout titleLayout = (LinearLayout) activity.findViewById(R.id.titleLayout);
        TextView title = (TextView) activity.findViewById(R.id.title);

        final EditText searchBox = (EditText) activity.findViewById(R.id.search);
        final HorizontalScrollView categoriesView = (HorizontalScrollView)
                activity.findViewById(R.id.categoriesView);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) titleLayout.getLayoutParams();

        int offset = searchBox.getBottom() - title.getBottom() - categoriesView.getTop();

        TranslateAnimation contentAnim = new TranslateAnimation(0f, 0f, 0f, offset);
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
                        categoriesView.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
                params.topMargin = searchBox.getHeight();
                categoriesView.setLayoutParams(params);
            }
        });

        categoriesView.startAnimation(contentAnim);
    }
}
