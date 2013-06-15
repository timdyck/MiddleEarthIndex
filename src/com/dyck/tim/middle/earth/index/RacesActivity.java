package com.dyck.tim.middle.earth.index;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class RacesActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        
      //Setup action bar
        ActionBar ab = getActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        final TabListener tabListener = new TabListener(){
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				Intent intent;  
				View view = findViewById(R.id.charactersList);
        		Bundle bundle = new Bundle();
        		if(tab.getPosition() == 0) //Main
        	    {
        	    	intent = new Intent(view.getContext(), MainActivity.class);
        	    }
        		else if(tab.getPosition() == 1) //Races
        	    {
        	    	intent = new Intent(view.getContext(), RacesActivity.class);
        	    	bundle.putString("Parent_Type", "Races");
        	    	return;
        	    }
        	    else if (tab.getPosition() == 2) //Places
        	    {
        	    	intent = new Intent(view.getContext(), PlacesActivity.class);
        	    	bundle.putString("Parent_Type", "Places");
        	    }
        	    else //Miscellaneous
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
        ab.addTab(racesTab, 1, true);
        ab.addTab(placesTab, 2, false);
        ab.addTab(miscTab, 3, false);
        
        
        final ListView characterListView = (ListView) findViewById(R.id.charactersList);
        String[] characterValues = 
        		new String[] { "Ainur", "Dragons", "Dwarves", "Elves", "Ents", 
        		"Hobbits", "Men", "Orcs", "Miscellaneous Races", "All" };
        
        final ArrayAdapter<String> characterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, characterValues);
        
        characterListView.setAdapter(characterAdapter); 
        
        characterListView.setOnItemClickListener(new OnItemClickListener() {
        	  @Override
        	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		Intent charIntent;  
          		Bundle bundle = new Bundle();
          	    if(position == 0) //Ainur
          	    {
          	    	charIntent = new Intent(view.getContext(), ListActivity.class);
          	    	bundle.putString("Type", "Ainur");
          	    }
          	    else if (position == 1) //Dragons
          	    {
          	    	charIntent = new Intent(view.getContext(), ListActivity.class);
          	    	bundle.putString("Type", "Dragons");
          	    }
          	    else if (position == 2) //Dwarves
          	    {
          	    	charIntent = new Intent(view.getContext(), ListActivity.class);
          	    	bundle.putString("Type", "Dwarves");
          	    }
          	    else if (position == 3) //Elves
          	    {
          	    	charIntent = new Intent(view.getContext(), ListActivity.class);
          	    	bundle.putString("Type", "Elves");
          	    }
          	    else if (position == 4) //Ents
        	    {
        	    	charIntent = new Intent(view.getContext(), ListActivity.class);
        	    	bundle.putString("Type", "Ents");
        	    }
          	    else if (position == 5) //Hobbits
	      	    {
	      	    	charIntent = new Intent(view.getContext(), ListActivity.class);
	      	    	bundle.putString("Type", "Hobbits");
	      	    }
	          	else if (position == 6) //Men
	    	    {
	    	    	charIntent = new Intent(view.getContext(), ListActivity.class);
	    	    	bundle.putString("Type", "Men");
	    	    }
	          	else if (position == 7) //Orcs
	    	    {
	    	    	charIntent = new Intent(view.getContext(), ListActivity.class);
	    	    	bundle.putString("Type", "Orcs");
	    	    }
	          	else if (position == 8) //Miscellaneous Races
	    	    {
	    	    	charIntent = new Intent(view.getContext(), ListActivity.class);
	    	    	bundle.putString("Type", "Miscellaneous Races");
	    	    }
          	    else
          	    {
          	    	charIntent = new Intent(view.getContext(), ListActivity.class);
          	    	bundle.putString("Type", "Races: All");
          	    }
          	    
          	    bundle.putString("Parent_Type", "Races");
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
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_SEARCH)
        {
        }
        else if(keyCode == KeyEvent.KEYCODE_MENU)
        {
        	AlertDialog ad = MyInfoDialog.create(this);
            ad.show();
            //Make URL clickable
            ((TextView)ad.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
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
            ((TextView)ad.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
        default:
            return true;
        }
    }
}
