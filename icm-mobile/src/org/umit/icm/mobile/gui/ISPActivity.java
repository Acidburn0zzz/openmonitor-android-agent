/**
 * Copyright (C) 2011 Adriano Monteiro Marques
 *
 * Author:  Zubair Nabi <zn.zubairnabi@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package org.umit.icm.mobile.gui;

import org.umit.icm.mobile.Main;
import org.umit.icm.mobile.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ISPActivity extends Activity{
    /** Called when the activity is first created. */
	private ListView listView;
	
	private Button backButton;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.ispactivity);
        listView = (ListView)findViewById(R.id.ListView01);
        
        String listViewItem[] = {getString(R.string.list_websites),getString(R.string.list_services)};
        listView.setAdapter(new ArrayAdapter<String>(this
        		,android.R.layout.simple_list_item_1
        		, listViewItem));
        
        backButton = (Button) findViewById(R.id.backButton);
        
        backButton.setOnClickListener(new OnClickListener() { 
	       	public void onClick(View v) {  	                		 
	       		Intent intent = new Intent(ISPActivity.this, Main.class);	       		
	            startActivity(intent); 
	       	}

	   	}  );
    }
}