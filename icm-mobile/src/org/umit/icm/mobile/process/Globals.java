/**
 * Copyright (C) 2011 Adriano Monteiro Marques
 *
 * Author:  Zubair Nabi <zn.zubairnabi@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either Test 2 of the License, or
 * (at your option) any later Test.
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

package org.umit.icm.mobile.process;

import java.util.HashMap;
import java.util.Map;

import org.umit.icm.mobile.connectivity.ServiceConnectivity;
import org.umit.icm.mobile.connectivity.TCPClient;
import org.umit.icm.mobile.connectivity.TCPServer;
import org.umit.icm.mobile.p2p.AuthenticatedPeers;
import org.umit.icm.mobile.p2p.MessageQueue;
import org.umit.icm.mobile.social.TwitterUpdate;

/**
 * Holds application wide Globals.
 */

public class Globals {
	/**
	 * Global {@link RuntimeParameters}
	 */
	public static RuntimeParameters runtimeParameters 
	= new RuntimeParameters();
	
	/**
	 * Global {@link TwitterUpdate}
	 */
	public static TwitterUpdate twitterUpdate 
	= new TwitterUpdate();		
	
	/**
	 * Global {@link VersionManager}
	 */
	public static VersionManager versionManager 
	= new VersionManager();
	
	/**
	 * Global {@link KeyManager}
	 */
	public static KeyManager keyManager
	= new KeyManager();

	/**
	 * Global {@link String} to hold scan status
	 */
	public static String scanStatus = " ";
	
	
	public static String challenge = "";


	/**
	 * Global {@link TCPServer}
	 */
	public static TCPServer tcpServer;	
	
	/**
	 * Global {@link TCPClient}
	 */
	public static TCPClient tcpClient
	= new TCPClient();
	
	/**
	 * Global {@link TCPClient} used for Connectivity Tests
	 */
	public static TCPClient tcpClientConnectivity
	= new TCPClient();		
	
	/**
	 * Global {@link String} to select map view
	 */
	public static String mapView = "Google";
	
	/**
	 * Global {@link ServiceConnectivity}
	 */
	public static ServiceConnectivity serviceTest 
	= new ServiceConnectivity();
	
	/**
	 * Global HashMap to hold services packets
	 */
	public static Map<String, String> servicePacketsMap 
	= new HashMap <String, String>();
	
	/**
	 * Global IP address of app.
	 */
	public static String myIP = "255.255.255.0";
	
	/**
	 * Global boolean object to check if aggregator is reachable.
	 */
	public static boolean aggregatorCommunication = true;
	

	public static boolean isRegistered = false;
	
	public static boolean isLoggedIn = false;
	
	/**
	 * Global P2P message queue
	 */
	public static MessageQueue messageQ 
	= new MessageQueue();
		
	/**
	 * Global boolean object to check if p2p communication is possible.
	 */
	public static boolean p2pCommunication = false;
	
	/**
	 * Global authenticate peers object
	 */
	public static AuthenticatedPeers authenticatedPeers
	= new AuthenticatedPeers();
	
	public static android.location.Location currentLocationGPS;
	
	public static android.location.Location currentLocationNetwork;
	
	public static RuntimeLists runtimeList = new RuntimeLists();
}