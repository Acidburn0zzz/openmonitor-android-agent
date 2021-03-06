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

package org.umit.icm.mobile.connectivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.umit.icm.mobile.process.Globals;

import android.util.Log;

/**
 * This is the HTTPS Service class. Holds {@link ServiceHTTPS#connect()},
 * {@link ServiceHTTPS#getService()} and {@link ServiceHTTPS#getService()} methods.
 */

public class ServiceHTTPS implements AbstractServiceTest {
	
	/**
	 * Returns an HTTPS Response String.
	 * 
	 *	 	                           	                          		             	            
	           
	@return      String
	 *
	 
	@see HttpClient
	 */
	@Override
	public String connect() {
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(getServiceURL());		

		try {			
		    HttpResponse httpResponse = httpClient.execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
		      if (statusLine.getStatusCode() != 200) {
		          return "blocked";
		      }			      
		      HttpEntity httpEntity = httpResponse.getEntity();
		      InputStream inputStream = httpEntity.getContent();	
		      ByteArrayOutputStream byteArrayOutputStream 
		      = new ByteArrayOutputStream();			      
		      int bytes = 0;
		      byte[] buffer = new byte[1024];
		      while ((bytes = inputStream.read(buffer)) != -1) {
		    	  byteArrayOutputStream.write(buffer, 0, bytes);
		      }
		      if(new String(byteArrayOutputStream.toByteArray()) != null)
		    	  return "normal";
		      else
		    	  return "blocked";
		  }
		  catch (IOException e) {
			     Log.w("####", e.getLocalizedMessage());
			     return "blocked";
		}
	}
	
	/**
	 * Returns a {@link Service} object for HTTPS. 
	 * 
	 *	 

	@see  Service
	 *  	                          		              
	            
	@return      Service
	 */	
	@Override
	public Service getService() {
		return Globals.runtimeList.servicesList.get(0);
	}
	
	/**
	 * Returns a String for service scanning URL. 
	 * 
	 *	  	                          		              
	            
	@return      String
	 */	
	@Override
	public String getServiceURL() {
		return "https://campusmail.lums.edu.pk";
	}

	@Override
	public String getServicePacket() {
		return ServicePackets.HTTP_GET;
	}
}
