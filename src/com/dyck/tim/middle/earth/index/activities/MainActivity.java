package com.dyck.tim.middle.earth.index.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dyck.tim.middle.earth.index.R;
import com.dyck.tim.middle.earth.index.animations.Animations;
import com.dyck.tim.middle.earth.index.animations.CategoryAnimations;
import com.dyck.tim.middle.earth.index.animations.ContentAnimations;
import com.dyck.tim.middle.earth.index.animations.TitleAnimations;
import com.dyck.tim.middle.earth.index.data.DatabaseHelper;

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


        // Initialize database
        final DatabaseHelper database = new DatabaseHelper(instance);
        database.openDataBase();

        setContentView(R.layout.activity_main);

        //Setup categories
        final LinearLayout categoriesLayout = (LinearLayout) findViewById(R.id.categoriesLayout);
        for (final Category category : Category.values()) {
            Button button = new Button(getApplicationContext());
            button.setText(category.name());

            // Add button click behaviour
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Animate
                    categoryClickAnimations(instance);

                    // Populate content list
                    List<String> contentList = new ArrayList<String>();
                    Cursor column = database.getColumn(category.name());

                    for(column.moveToFirst() ; !column.isAfterLast() ; column.moveToNext()){
                        contentList.add(column.getString(0));
                    }

                    ListView content = (ListView) findViewById(R.id.contentList);
                    ArrayAdapter listAdapter = new ArrayAdapter<String>(
                            instance, android.R.layout.simple_list_item_1, contentList);

                    content.setAdapter(listAdapter);
                }
            });

            categoriesLayout.addView(button);
        }

        //Content List (For testing purposes)

        // Clear focus
        EditText search = (EditText) findViewById(R.id.search);
        search.clearFocus();
    }

    /**
     * Performs animations on all of the layout pieces to transition to a new state. Note that the
     * state only changes on category click if you are in the {@link State#HOME} state, which then
     * transitions to the {@link State#CONTENT} state.
     *
     * @param instance Main activity instance
     */
    private void categoryClickAnimations(Activity instance) {
        if (!currentState.equals(State.HOME)) {
            return;
        }

        List<Animations> animations = new ArrayList<Animations>();
        animations.add(new TitleAnimations(instance));
        animations.add(new CategoryAnimations(instance));
        animations.add(new ContentAnimations(instance));

        int duration = 1000;

        for (Animations animation : animations) {
            animation.homeToContent(duration);
        }

        currentState = State.CONTENT;
    }

}
