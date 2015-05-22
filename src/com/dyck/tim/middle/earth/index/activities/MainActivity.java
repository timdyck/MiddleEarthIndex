package com.dyck.tim.middle.earth.index.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dyck.tim.middle.earth.index.R;
import com.dyck.tim.middle.earth.index.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

@TargetApi(12)
public class MainActivity extends Activity {

    MainActivity instance = this;

    State currentState = State.HOME;
    DatabaseHelper database = new DatabaseHelper(this);

    @TargetApi(12)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // Initialize database
        database.open();

        //Setup categories
        final LinearLayout categoriesLayout = (LinearLayout) findViewById(R.id.categoriesLayout);
        for (final Category category : Category.values()) {
            Button button = new Button(getApplicationContext());
            button.setText(category.name());

            // Add button click behaviour
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                        currentState = Transition.HOME_TO_CONTENT
                                .performTransitionAnimations(instance, currentState);

                    populateContentList(instance, category);
                }
            });

            categoriesLayout.addView(button);
        }

        // Set button click for outside of content view
        FrameLayout sideFrameLeft = (FrameLayout) findViewById(R.id.frameLeft);
        FrameLayout sideFrameRight = (FrameLayout) findViewById(R.id.frameRight);

        View.OnClickListener frameListener = new View.OnClickListener() {
            public void onClick(View v) {
                    currentState = Transition.CONTENT_TO_HOME
                            .performTransitionAnimations(instance, currentState);
            }
        };

        sideFrameLeft.setOnClickListener(frameListener);
        sideFrameRight.setOnClickListener(frameListener);

        // Set button click for list
        ListView content = (ListView) findViewById(R.id.contentList);

        AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentState = Transition.CONTENT_TO_DATA
                        .performTransitionAnimations(instance, currentState);
            }
        };

        content.setOnItemClickListener(listListener);

        // Clear focus
        EditText search = (EditText) findViewById(R.id.search);
        search.clearFocus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        database.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        database.close();
    }

    private void populateContentList(Activity instance, Category category) {
        List<String> contentList = new ArrayList<String>();
        Cursor column = database.getColumn(category.name());

        for (column.moveToFirst(); !column.isAfterLast(); column.moveToNext()) {
            contentList.add(column.getString(0));
        }

        ListView content = (ListView) findViewById(R.id.contentList);
        ArrayAdapter listAdapter = new ArrayAdapter<String>(
                instance, android.R.layout.simple_list_item_1, contentList);

        content.setAdapter(listAdapter);
    }

}
