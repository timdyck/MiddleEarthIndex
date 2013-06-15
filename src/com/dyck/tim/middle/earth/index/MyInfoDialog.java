package com.dyck.tim.middle.earth.index;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.util.Linkify;

public class MyInfoDialog {
	
	@SuppressWarnings("deprecation")
	public static AlertDialog create(Context context) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle("Information");
	    final SpannableString s = new SpannableString("Even as a Tolkien fanatic, it is often tough to remember all the names and places when reading his extensive works.  Here is your glossary of all things Tolkien, with over 5000 entries!\n\nAll information taken from 'The Encyclopedia of Arda', with no copyright infringement intended.\n\nhttp://www.glyphweb.com/arda/default.asp");
	    Linkify.addLinks(s, Linkify.WEB_URLS);
	    alertDialog.setMessage(s);
	    alertDialog.setIcon(android.R.drawable.ic_menu_info_details);
	    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {  
	    	   public void onClick(final DialogInterface dialog, final int id) {  
	    	     dialog.cancel(); 
	    	}  
		});
	    return alertDialog;
	}
}
