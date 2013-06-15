package com.dyck.tim.middle.earth.index;

import android.os.Bundle;
import android.annotation.TargetApi;
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

@TargetApi(11)
public class MiscellaneousActivity extends Activity {

	@TargetApi(11)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miscellaneous);
        
      //Setup action bar
        ActionBar ab = getActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        final TabListener tabListener = new TabListener(){
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				Intent intent;  
				View view = findViewById(R.id.miscellaneousList);
        		Bundle bundle = new Bundle();
        		if(tab.getPosition() == 0) //Main
        	    {
        	    	intent = new Intent(view.getContext(), MainActivity.class);
        	    }
        		else if(tab.getPosition() == 1) //Races
        	    {
        	    	intent = new Intent(view.getContext(), RacesActivity.class);
        	    	bundle.putString("Parent_Type", "Races");
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
        	    	return;
        	    }
        	    
        	    intent.putExtras(bundle);
      		  	startActivity(intent);
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
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
        ab.addTab(placesTab, 2, false);
        ab.addTab(miscTab, 3, true);
        
        final ListView miscellaneousListView = (ListView) findViewById(R.id.miscellaneousList);
        String[] miscellaneousValues = 
        		new String[] { "Animals and Plants", "Events", "Items",
        		"Languages", "Songs, Lays and Tales", "Time and Calendars", "Others", "All" };
        
        final ArrayAdapter<String> characterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, miscellaneousValues);
        
        miscellaneousListView.setAdapter(characterAdapter); 
        
        miscellaneousListView.setOnItemClickListener(new OnItemClickListener() {
        	  @Override
        	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		Intent charIntent;  
          		Bundle bundle = new Bundle();
          		
          	    if (position == 0) //Animals and Plants
          	    {
          	    	charIntent = new Intent(view.getContext(), ListActivity.class);
          	    	bundle.putString("Type", "Animals and Plants");
          	    }
          	    else if (position == 1) //Events
          	    {
          	    	charIntent = new Intent(view.getContext(), ListActivity.class);
          	    	bundle.putString("Type", "Events");
          	    }
          	    else if (position == 2) //Items
          	    {
          	    	charIntent = new Intent(view.getContext(), ListActivity.class);
          	    	bundle.putString("Type", "Items");
          	    }
          	    else if (position == 3) //Languages
        	    {
        	    	charIntent = new Intent(view.getContext(), ListActivity.class);
        	    	bundle.putString("Type", "Languages");
        	    }
          	    else if (position == 4) //Songs, Lays and Tales
	      	    {
	      	    	charIntent = new Intent(view.getContext(), ListActivity.class);
	      	    	bundle.putString("Type", "Songs, Lays and Tales");
	      	    }
	          	else if (position == 5) //Times and Calendars
	    	    {
	    	    	charIntent = new Intent(view.getContext(), ListActivity.class);
	    	    	bundle.putString("Type", "Time and Calendars");
	    	    }
	          	else if (position == 6) //Others
	    	    {
	    	    	charIntent = new Intent(view.getContext(), ListActivity.class);
	    	    	bundle.putString("Type", "Others");
	    	    }
          	    else
          	    {
          	    	charIntent = new Intent(view.getContext(), ListActivity.class);
          	    	bundle.putString("Type", "Miscellaneous: All");
          	    }
          	    
          	    bundle.putString("Parent_Type", "Miscellaneous");
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
    
	@TargetApi(11)
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
