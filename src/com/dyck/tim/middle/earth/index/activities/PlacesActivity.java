package com.dyck.tim.middle.earth.index.activities;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.dyck.tim.middle.earth.index.R;

public class PlacesActivity extends Activity {

    @TargetApi(11)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        //Setup action bar
        ActionBar ab = getActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        final TabListener tabListener = new TabListener() {
            @Override
            public void onTabSelected(Tab tab, FragmentTransaction ft) {
                Intent intent;
                View view = findViewById(R.id.placesList);
                Bundle bundle = new Bundle();
                if (tab.getPosition() == 0) //Main
                {
                    intent = new Intent(view.getContext(), MainActivity.class);
                } else if (tab.getPosition() == 1) //Races
                {
                    intent = new Intent(view.getContext(), RacesActivity.class);
                    bundle.putString("Parent_Type", "Races");
                } else if (tab.getPosition() == 2) //Places
                {
                    intent = new Intent(view.getContext(), PlacesActivity.class);
                    bundle.putString("Parent_Type", "Places");
                    return;
                } else //Miscellaneous
                {
                    intent = new Intent(view.getContext(), MiscellaneousActivity.class);
                    bundle.putString("Parent_Type", "Miscellaneous");
                }

                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onTabReselected(Tab tab, FragmentTransaction ft) {
            }

            @Override
            public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            }
        };

        ActionBar.Tab mainTab = ab.newTab();
        mainTab.setText("Main").setTabListener(tabListener);
        ActionBar.Tab racesTab = ab.newTab();
        racesTab.setText("Races").setTabListener(tabListener);
        ActionBar.Tab placesTab = ab.newTab();
        placesTab.setText("Places").setTabListener(tabListener);
        ActionBar.Tab miscTab = ab.newTab();
        miscTab.setText("Misc.").setTabListener(tabListener);

        ab.addTab(mainTab, 0, false);
        ab.addTab(racesTab, 1, false);
        ab.addTab(placesTab, 2, true);
        ab.addTab(miscTab, 3, false);


        final ListView placesListView = (ListView) findViewById(R.id.placesList);
        String[] placesValues =
                new String[]{"Cities and Buildings", "Fields, Plains and Deserts", "Forests",
                        "Hills and Mountains", "Islands and Promontories", "Lands, Realms and Regions",
                        "Rivers and Lakes", "Seas and Oceans", "Miscellaneous Places", "All"};

        final ArrayAdapter<String> characterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, placesValues);

        placesListView.setAdapter(characterAdapter);

        placesListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent charIntent;
                Bundle bundle = new Bundle();
                if (position == 0) //Cities and Buildings
                {
                    charIntent = new Intent(view.getContext(), ListActivity.class);
                    bundle.putString("Type", "Cities and Buildings");
                } else if (position == 1) //Fields, Plains and Deserts
                {
                    charIntent = new Intent(view.getContext(), ListActivity.class);
                    bundle.putString("Type", "Fields, Plains and Deserts");
                } else if (position == 2) //Forests
                {
                    charIntent = new Intent(view.getContext(), ListActivity.class);
                    bundle.putString("Type", "Forests");
                } else if (position == 3) //Hills and Mountains
                {
                    charIntent = new Intent(view.getContext(), ListActivity.class);
                    bundle.putString("Type", "Hills and Mountains");
                } else if (position == 4) //Islands and Promontories
                {
                    charIntent = new Intent(view.getContext(), ListActivity.class);
                    bundle.putString("Type", "Islands and Promontories");
                } else if (position == 5) //Lands, Realms and Regions
                {
                    charIntent = new Intent(view.getContext(), ListActivity.class);
                    bundle.putString("Type", "Lands, Realms and Regions");
                } else if (position == 6) //Rivers and Lakes
                {
                    charIntent = new Intent(view.getContext(), ListActivity.class);
                    bundle.putString("Type", "Rivers and Lakes");
                } else if (position == 7) //Seas and Oceans
                {
                    charIntent = new Intent(view.getContext(), ListActivity.class);
                    bundle.putString("Type", "Seas and Oceans");
                } else if (position == 8) //Miscellaneous Places
                {
                    charIntent = new Intent(view.getContext(), ListActivity.class);
                    bundle.putString("Type", "Miscellaneous Places");
                } else {
                    charIntent = new Intent(view.getContext(), ListActivity.class);
                    bundle.putString("Type", "Places: All");
                }

                bundle.putString("Parent_Type", "Places");
                charIntent.putExtras(bundle);

                startActivity(charIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            AlertDialog ad = MyInfoDialog.create(this);
            ad.show();
            //Make URL clickable
            ((TextView) ad.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search:
                final SearchView searchView = (SearchView) item.getActionView();

                final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        // Do something
                        return true;
                    }

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Intent searchIntent = new Intent(searchView.getContext(), ListActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("Query", query);
                        bundle.putString("Type", "Search");
                        searchIntent.putExtras(bundle);

                        startActivity(searchIntent);
                        return true;
                    }
                };
                searchView.setOnQueryTextListener(queryTextListener);
                return true;
            case R.id.information:
                AlertDialog ad = MyInfoDialog.create(this);
                ad.show();
                //Make URL clickable
                ((TextView) ad.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
            default:
                return true;
        }
    }
}
