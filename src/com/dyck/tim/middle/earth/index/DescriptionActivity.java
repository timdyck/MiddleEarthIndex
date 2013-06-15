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
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

public class DescriptionActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        
        Bundle b = getIntent().getExtras();
        
      //Setup action bar
        ActionBar ab = getActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        final TabListener tabListener = new TabListener(){
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				Intent intent;  
				View view = findViewById(R.id.Description);
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
        	    }
        	    
        	    intent.putExtras(bundle);
      		  	startActivity(intent);
			}
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				Intent intent;  
				View view = findViewById(R.id.Description);
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
        	    }
        	    
        	    intent.putExtras(bundle);
      		  	startActivity(intent);
			}
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			}
        };
        
        final TabListener dummyTabListener = new TabListener(){
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
			}
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
			}
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			}
        };
        
        ActionBar.Tab mainTab = ab.newTab();
        mainTab.setText("Main").setTabListener(dummyTabListener);
        ActionBar.Tab racesTab = ab.newTab();
        racesTab.setText("Races").setTabListener(dummyTabListener);
        ActionBar.Tab placesTab = ab.newTab();
        placesTab.setText("Places").setTabListener(dummyTabListener);
        ActionBar.Tab miscTab = ab.newTab();
        miscTab.setText("Misc.").setTabListener(dummyTabListener);
        
        ab.addTab(mainTab, 0, false);
        ab.addTab(racesTab, 1, false);
        ab.addTab(placesTab, 2, false);
        ab.addTab(miscTab, 3, false);
        
        if (b.getString("Parent_Type") != null)
        {
	        if (b.getString("Parent_Type").equals("Races"))
	        	ab.setSelectedNavigationItem(1);
	        else if (b.getString("Parent_Type").equals("Places"))
	        	ab.setSelectedNavigationItem(2);
	        else if (b.getString("Parent_Type").equals("Miscellaneous"))
	        	ab.setSelectedNavigationItem(3);
	        else
	        	ab.setSelectedNavigationItem(0);
        }
        else
        	ab.setSelectedNavigationItem(0);
        
        mainTab.setTabListener(tabListener);
        racesTab.setTabListener(tabListener);
        placesTab.setTabListener(tabListener);
        miscTab.setTabListener(tabListener);
        
        TextView myTextTitle = (TextView)findViewById(R.id.descriptionTitle);
        myTextTitle.setText(b.getString("Name"));

        TextView myText = (TextView) findViewById(R.id.Description);
        myText.setMovementMethod(new ScrollingMovementMethod());
        myText.setText("\t" + b.getString("Description"));

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
