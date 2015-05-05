package com.dyck.tim.middle.earth.index.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dyck.tim.middle.earth.index.R;

@TargetApi(11)
public class MainActivity extends Activity {

    @TargetApi(11)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup categories
        final LinearLayout categoriesLayout = (LinearLayout) findViewById(R.id.categoriesLayout);
        for (Category category : Category.values()) {
            Button button = new Button(getApplicationContext());
            button.setText(category.name());
            categoriesLayout.addView(button);
        }
    }

}
