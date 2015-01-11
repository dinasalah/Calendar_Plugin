package com.onbond.calendar;

import java.util.Date;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.text.format.DateUtils;
import android.util.Log;

public class Calendar_plugin extends CordovaPlugin {

	   public static final String ACTION_ADD_CALENDAR_ENTRY = "addCalendarEvent";
		private String selected_email="test";
		CallbackContext callbackContext;
	    @Override
	    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	        try {
	        	this.callbackContext = callbackContext;
	            if (ACTION_ADD_CALENDAR_ENTRY.equals(action)) { 

/*
 * 
 * 
 * */
	            	Intent i = new Intent();

	           	 // mimeType will popup the chooser any  for any implementing application (e.g. the built in calendar or applications such as "Business calendar"
	           	 i.setType("vnd.android.cursor.item/event"); 

	           	 // the time the event should start in millis. This example uses now as the start time and ends in 1 hour
	           	 i.putExtra("beginTime", new Date().getTime()); 
	           	 i.putExtra("endTime", new Date().getTime() + DateUtils.HOUR_IN_MILLIS);

	           	 // the action
	           	 i.setAction(Intent.ACTION_EDIT);
	                this.cordova.startActivityForResult((CordovaPlugin) this, i, 1009);

	            	
			    	    return true;
				}

				callbackContext.error("Invalid action");
				return false;
				} 
			catch(Exception e) {
				System.err.println("Exception: " + e.getMessage());
				callbackContext.error(e.getMessage());
				return false;
				}
	    }
	    
	    public void onActivityResult(int requestCode, int resultCode, Intent data) 
	    {
	    	Log.i("resultCode:", String.valueOf(resultCode));
	        
	    	if (resultCode == -1) {
	            
	        	Cursor cursor = null;
	                try {
	                	Uri result = data.getData();
	                    Log.v("DEBUG_TAG", "Got a contact result: " + result.toString());

	                    String id = result.getLastPathSegment();

	                    
	                    cursor = this.cordova.getActivity().getApplicationContext().getContentResolver().query(Email.CONTENT_URI,  null, BaseColumns._ID + "=" + id, null, null);
	                    int emailIdx = cursor.getColumnIndex(Email.DATA);
	                    if (cursor.moveToFirst()) {
	                    	selected_email = cursor.getString(emailIdx);
	                        Log.v("DEBUG_TAG", "Got email: " + selected_email);
	    		    	    this.callbackContext.success(selected_email);

	                    } 
	                    
	                    else {
	                        Log.w("DEBUG_TAG", "No results");
	                    }
	                } catch (Exception e) {
	                    Log.e("DEBUG_TAG", "Failed to get email data", e);
	                } finally {
	                    if (cursor != null) {
	                        cursor.close();
	                }
	                }
	            

	        } else {
	            Log.w("DEBUG_TAG", "Warning: activity result not ok");
	        }   
	    }
	    
	    public String getEmailSelected()
	    {
	    	return selected_email;
	    }

	}

