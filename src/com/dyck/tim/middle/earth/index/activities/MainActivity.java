package com.dyck.tim.middle.earth.index.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dyck.tim.middle.earth.index.R;

import java.util.ArrayList;
import java.util.List;

@TargetApi(11)
public class MainActivity extends Activity {

    @TargetApi(11)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        //Setup categories
        LinearLayout categoriesLayout = (LinearLayout) findViewById(R.id.categoriesLayout);
        for (Category category : Category.values()) {
            Button button = new Button(getApplicationContext());
            button.setText(category.name());

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ListView content = (ListView) findViewById(R.id.contentList);
                    content.setVisibility(View.VISIBLE);
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

    }

}
