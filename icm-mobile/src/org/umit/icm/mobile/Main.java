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

package org.umit.icm.mobile;


import java.io.IOException;

import org.umit.icm.mobile.R;
import org.umit.icm.mobile.connectivity.ConnectivityService;
import org.umit.icm.mobile.connectivity.ServicePackets;
import org.umit.icm.mobile.gui.ControlActivity;
import org.umit.icm.mobile.gui.InformationActivity;
import org.umit.icm.mobile.gui.MapActivityTab;
import org.umit.icm.mobile.notifications.NotificationService;
import org.umit.icm.mobile.p2p.MessageBuilder;
import org.umit.icm.mobile.process.Globals;
import org.umit.icm.mobile.process.Initialization;
import org.umit.icm.mobile.proto.MessageProtos.AuthenticatePeer;
import org.umit.icm.mobile.proto.MessageProtos.AuthenticatePeerResponse;
import org.umit.icm.mobile.utils.AESCrypto;
import org.umit.icm.mobile.utils.Constants;
import org.umit.icm.mobile.utils.ProfilerRun;
import org.umit.icm.mobile.utils.SDCardReadWrite;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

/**
 * The main entry point of the application. Populates the Tabs and initializes
 * default parameters and runs services.
 */

public class Main extends TabActivity {
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Resources resources = getResources(); 
        TabHost tabHost = getTabHost();
        
        Intent intent = new Intent().setClass(this, InformationActivity.class)
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(getString(R.string.tab_information)).setIndicator(getString(R.string.tab_information),
                          resources.getDrawable(R.drawable.tabs_icons)).setContent(intent);
        tabHost.addTab(tabSpec);
        
        intent = new Intent().setClass(this, MapActivityTab.class)
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tabSpec = tabHost.newTabSpec(getString(R.string.tab_map)).setIndicator(getString(R.string.tab_map),
                          resources.getDrawable(R.drawable.tabs_icons)).setContent(intent);
        tabHost.addTab(tabSpec);

        intent = new Intent().setClass(this, ControlActivity.class);
        tabSpec = tabHost.newTabSpec(getString(R.string.tab_control)).setIndicator(getString(R.string.tab_control),
                          resources.getDrawable(R.drawable.tabs_icons)).setContent(intent);
        tabHost.addTab(tabSpec);
    
        tabHost.setCurrentTab(0);
       
        if(!SDCardReadWrite.checkSDCard()) {
        	String text = getString(R.string.no_sdcard);
        	int duration = Toast.LENGTH_LONG;
    		Toast toast = Toast.makeText(this, text, duration);
    		toast.show();
    		moveTaskToBack(true);        	
        } else {        	            	      			                         
	        try { /*Register Agent should be called here*/
	        	Globals.keyManager.setMySecretKey(AESCrypto.generateKey("password".getBytes()));
				Initialization.checkFiles();		
				Initialization.startServices(Main.this);
				Initialization.checkProfiler();
				Globals.scanStatus = getString(R.string.scan_on);								
				ServicePackets.populateServicesMap();				
				/*AuthenticatePeer authenticatePeer = AuthenticatePeer.newBuilder()
				.setAgentID(10)
				.setAgentPort(8000)
				.setCipheredPublicKey("cipheredPublicKey")
				.build();
				Globals.tcpClientConnectivity.openConnection("202.206.64.11", 3128);
				//Globals.tcpClientConnectivity.writeLine("hello");
				Globals.tcpClientConnectivity.writeLine(MessageBuilder.generateMessage(
						1, authenticatePeer.toByteArray()));
				byte [] response = Globals.tcpClientConnectivity.readBytes();
				byte [] idbyte = new byte[4];
				idbyte[0] = response[0];
				idbyte[1] = response[1];
				idbyte[2] = response[2];
				idbyte[3] = response[3];
				int id = MessageBuilder.byteArrayToInt(idbyte);
				//AuthenticatePeerResponse authenticatePeerResponse
				//= AuthenticatePeerResponse.parseFrom();
				Log.w("###Main_read", Integer.toString(id));				
				Globals.tcpClientConnectivity.closeConnection();
		        	*/			
			} catch (IOException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	            
        }
    }
   
}