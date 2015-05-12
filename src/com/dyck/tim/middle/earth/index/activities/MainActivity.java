package com.dyck.tim.middle.earth.index.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dyck.tim.middle.earth.index.R;
import com.dyck.tim.middle.earth.index.animations.CategoryAnimations;
import com.dyck.tim.middle.earth.index.animations.TitleAnimations;

import java.util.ArrayList;
import java.util.List;

@TargetApi(12)
public class MainActivity extends Activity {

    State currentState;

    @TargetApi(12)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        final MainActivity instance = this;
        currentState = State.HOME;

        setContentView(R.layout.activity_main);

        //Setup categories
        final LinearLayout categoriesLayout = (LinearLayout) findViewById(R.id.categoriesLayout);
        for (Category category : Category.values()) {
            Button button = new Button(getApplicationContext());
            button.setText(category.name());

            // Add button click behaviour
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Move layout based on state
                    int duration = 1000;
                    switch (currentState) {
                        case HOME:
                            // Remove title
                            TitleAnimations titleAnim = new TitleAnimations(instance);
                            titleAnim.slideUp(duration);

                            // Slide up categories
                            CategoryAnimations categoryAnim = new CategoryAnimations(instance);
                            categoryAnim.slideUp(duration);

                            // Slide up content

                            currentState = State.CONTENT;
                            break;
                        case CONTENT:
                            break;
                        case DATA:
                            break;
                    }

                    // Populate listview with corresponding content
                }
            });

            categoriesLayout.addView(button);
        }

        //Content List (For viewing purposes)
        ListView content = (ListView) findViewById(R.id.contentList);
        List<String> sample = new ArrayList<String>();
        sample.add("THis");
        sample.add("is");
        sample.add("a");
        sample.add("a");
        sample.add("a");
        sample.add("a");
        sample.add("a");
        sample.add("a");
        sample.add("a");
        sample.add("a");
        sample.add("test");

        ArrayAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sample);
        content.setAdapter(listAdapter);

        // Clear focus
        EditText search = (EditText) findViewById(R.id.search);
        search.clearFocus();
    }

}
