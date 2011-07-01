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

package org.umit.icm.mobile.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.umit.icm.mobile.connectivity.Service;
import org.umit.icm.mobile.connectivity.Website;
import org.umit.icm.mobile.proto.MessageProtos.AgentData;
import org.umit.icm.mobile.proto.MessageProtos.Event;
import org.umit.icm.mobile.proto.MessageProtos.GetEventsResponse;
import org.umit.icm.mobile.proto.MessageProtos.GetPeerListResponse;
import org.umit.icm.mobile.proto.MessageProtos.GetSuperPeerListResponse;
import org.umit.icm.mobile.proto.MessageProtos.NewTestsResponse;
import org.umit.icm.mobile.proto.MessageProtos.ResponseHeader;
import org.umit.icm.mobile.proto.MessageProtos.Test;
import org.umit.icm.mobile.proto.MessageProtos.WebsiteReport;

import android.os.Environment;



public class SDCardReadWrite {
	private static File sdCard;
	
	public static void writeString(String fileName
			, String dir, String data) throws IOException, RuntimeException{
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
		keyDir.mkdirs();
    	File file = new File(keyDir, fileName);
    	if(!file.exists()){
    		file.createNewFile();
    	}
    		FileWriter fileWriter = new FileWriter(file, false);
    		try {
    			fileWriter.write(data);
			
    		} catch (Exception e) {
		    throw new RuntimeException("SDCardWrite exception", e);
    		} finally {
			fileWriter.close();
    		}
		}
	
	public static void writeStringAppend(String fileName
			, String dir, String data) throws IOException, RuntimeException{
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
		keyDir.mkdirs();
    	File file = new File(keyDir, fileName);
    	if(!file.exists()){
    		file.createNewFile();
    	}
    		FileWriter fileWriter = new FileWriter(file, true);
    		try {
    			fileWriter.write(data);
			
    		} catch (Exception e) {
		    throw new RuntimeException("SDCardWrite exception", e);
    		} finally {
			fileWriter.close();
    		}
		}
	
	public static String readString(String fileName
			, String dir) throws IOException, RuntimeException{
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
    	File file = new File(keyDir, fileName);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader); 
		try {
			return bufferedReader.readLine();
			
		} catch (Exception e) {
		    throw new RuntimeException("SDCardRead exception", e);
  	    } finally {
			fileReader.close();
		}
	}
	
	public static boolean fileExists(String fileName
			, String dir) throws IOException, RuntimeException{
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
    	File file = new File(keyDir, fileName);
    	if(file.exists()){
    		return true;
    	}
    	return false;
	}
	
	public static boolean fileNotEmpty(String fileName
			, String dir) throws IOException, RuntimeException{
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
    	File file = new File(keyDir, fileName);
    	FileReader fileReader = new FileReader(file);
    	BufferedReader bufferedReader = new BufferedReader(fileReader);
    	if(bufferedReader.readLine() == null){
    		return false;
    	}
    	return true;
	}
	
	public static void writeWebsite(String dir
			, Website data) throws IOException , RuntimeException{
		ObjectOutputStream objOutStream = null;
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
		keyDir.mkdirs();
    	File file = new File(keyDir, data.getUrl()
    			+ Constants.WEBSITE_FILE);
    	if(!file.exists()){
    		file.createNewFile();
    	}
    	try {
			objOutStream = new ObjectOutputStream(
				    new BufferedOutputStream(new FileOutputStream(file)));
			objOutStream.writeObject(data.getUrl());
			objOutStream.writeObject(data.getCheck());
			objOutStream.writeObject(data.getStatus());
    	} catch (Exception e) {
  		    throw new RuntimeException("writeWebsite exception", e);
  	  	} finally {
    		objOutStream.close();
    	}
	}
	
	public static Website readWebsite(String dir
			, String url) throws IOException , RuntimeException{
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
    	File file = new File(keyDir, url
    			+ Constants.WEBSITE_FILE);
    	Website website = new Website();
    	InputStream inputStream = new FileInputStream(file.toString());
  	  	ObjectInputStream objInputStream =
  	    new ObjectInputStream(new BufferedInputStream(inputStream));
  	  	try {
	    	    website.setUrl((String) objInputStream.readObject());
	    	    website.setCheck((String) objInputStream.readObject());
	    	    website.setStatus((String) objInputStream.readObject());
	    	    
	    	    return website;
  	  	} catch (Exception e) {
  		    throw new RuntimeException("readWebsite exception", e);
  	  	} finally {
  		  objInputStream.close();
  	  	}
	}
	
	public static boolean checkSDCard() {
		
		String storageState = android.os.Environment.getExternalStorageState();
		String mediaMounted = android.os.Environment.MEDIA_MOUNTED;
		if(storageState.equals(mediaMounted))
			return true;
		return false;
	}
	
	public static void writeWebsiteReport(String dir
			, WebsiteReport data) throws IOException, RuntimeException{
		OutputStream outputStream = null;
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
		keyDir.mkdirs();
    	File file = new File(keyDir
    			, data.getReport().getWebsiteURL().substring(11).replaceAll("/", "-")
    			+ Constants.WEBSITE_FILE);
    	if(!file.exists()){
    		file.createNewFile();
    	}
    	try {
			outputStream = new FileOutputStream(file);
			data.writeTo(outputStream);
			
    	} catch (Exception e) {
  		    throw new RuntimeException("write website exception", e);
  	  	} finally {
    		outputStream.close();
    	}
	}
	
	public static WebsiteReport readWebsiteReport(String dir
			, String url) throws IOException, RuntimeException{
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
    	File file = new File(keyDir
    			, url.substring(11).replaceAll("/", "-")
    			+ Constants.WEBSITE_FILE);
    	InputStream inputStream = new FileInputStream(file.toString());
  	  	
  	  	try {
	      		WebsiteReport websiteReport 
	      		= WebsiteReport.parseFrom(inputStream);
	      		return websiteReport;
  	  	} catch (Exception e) {
  		    throw new RuntimeException("read website exception", e);
  	  	} finally {
  		  inputStream.close();
  	  	}
	}
			
	public static void writeWebsitesList(String dir
			, List<Website> data) throws IOException, RuntimeException{
		ObjectOutputStream objOutStream = null;
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
		keyDir.mkdirs();
    	File file = new File(keyDir
    			, Constants.WEBSITES_LIST_FILE);
    	if(!file.exists()){
    		file.createNewFile();
    	}
    	try {
			objOutStream = new ObjectOutputStream(
				    new BufferedOutputStream(new FileOutputStream(file)));
			objOutStream.writeObject(data);
    	} catch (Exception e) {
  		    throw new RuntimeException("write websites list exception", e);
  	  	} finally {
    		objOutStream.close();
    	}
	}
	
	public static List<Website> readWebsitesList(String dir
			) throws IOException, RuntimeException{
		List<Website> websites = null;
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
    	File file = new File(keyDir
    			, Constants.WEBSITES_LIST_FILE);
    	InputStream inputStream = new FileInputStream(file.toString());
  	  	ObjectInputStream objInputStream =
  	    new ObjectInputStream(new BufferedInputStream(inputStream));
  	  	try {
	    	    websites = ((List<Website>) objInputStream.readObject());
	      		return websites;
  	  	} catch (Exception e) {
  		    throw new RuntimeException("read website exception", e);
  	  	} finally {
  		  inputStream.close();
  	  	}
	}
	
	public static void writeService(String dir
			, Service data) throws IOException , RuntimeException{
		ObjectOutputStream objOutStream = null;
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
		keyDir.mkdirs();
    	File file = new File(keyDir, data.getName()
    			+ Constants.SERVICE_FILE);
    	if(!file.exists()){
    		file.createNewFile();
    	}
    	try {
			objOutStream = new ObjectOutputStream(
				    new BufferedOutputStream(new FileOutputStream(file)));
			objOutStream.writeObject(data.getCheck());
			objOutStream.writeObject(data.getName());
			objOutStream.writeObject(data.getPorts());
			objOutStream.writeObject(data.getStatus());
    	} catch (Exception e) {
  		    throw new RuntimeException("writeService exception", e);
  	  	} finally {
    		objOutStream.close();
    	}
	}
	
	public static Service readService(String dir
			, String name) throws IOException , RuntimeException{
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
    	File file = new File(keyDir, name
    			+ Constants.SERVICE_FILE);
    	Service service = new Service();
    	InputStream inputStream = new FileInputStream(file.toString());
  	  	ObjectInputStream objInputStream =
  	    new ObjectInputStream(new BufferedInputStream(inputStream));
  	  	try {
	    	    service.setCheck((String) objInputStream.readObject());
	    	    service.setName((String) objInputStream.readObject());
	    	    service.setPorts((List<Integer>) objInputStream.readObject());
	    	    service.setStatus((String) objInputStream.readObject());
	    	    
	    	    return service;
  	  	} catch (Exception e) {
  		    throw new RuntimeException("readService exception", e);
  	  	} finally {
  		  objInputStream.close();
  	  	}
	}
	
	public static void writeServicesList(String dir
			, List<Service> data) throws IOException, RuntimeException{
		ObjectOutputStream objOutStream = null;
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
		keyDir.mkdirs();
    	File file = new File(keyDir
    			, Constants.SERVICES_LIST_FILE);
    	if(!file.exists()){
    		file.createNewFile();
    	}
    	try {
			objOutStream = new ObjectOutputStream(
				    new BufferedOutputStream(new FileOutputStream(file)));
			objOutStream.writeObject(data);
    	} catch (Exception e) {
  		    throw new RuntimeException("write services list exception", e);
  	  	} finally {
    		objOutStream.close();
    	}
	}
	
	public static List<Service> readServicesList(String dir
			) throws IOException, RuntimeException{
		List<Service> services = null;
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
    	File file = new File(keyDir
    			, Constants.SERVICES_LIST_FILE);
    	InputStream inputStream = new FileInputStream(file.toString());
  	  	ObjectInputStream objInputStream =
  	    new ObjectInputStream(new BufferedInputStream(inputStream));
  	  	try {
	    	    services = ((List<Service>) objInputStream.readObject());
	      		return services;
  	  	} catch (Exception e) {
  		    throw new RuntimeException("read services list exception", e);
  	  	} finally {
  		  inputStream.close();
  	  	}
	}
	
	public static void writePeersList(String dir
			, List<AgentData> data) throws IOException, RuntimeException{
		ObjectOutputStream objOutStream = null;		
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
		keyDir.mkdirs();
    	File file = new File(keyDir
    			, Constants.PEERS_FILE);
    	if(!file.exists()){
    		file.createNewFile();
    	}
    	
    	ResponseHeader responseHeader = ResponseHeader.newBuilder()
    	.setCurrentTestVersionNo(21)
    	.setCurrentVersionNo(21)
    	.build();
    	
    	GetPeerListResponse getPeerListResponse 
    	= GetPeerListResponse.newBuilder()
    	.setHeader(responseHeader)
    	.addAllKnownPeers(data)    	
    	.build();
    	    	
    	try {
			objOutStream = new ObjectOutputStream(
				    new BufferedOutputStream(new FileOutputStream(file)));									
			 getPeerListResponse.writeTo(objOutStream);				 
				
    	} catch (Exception e) {
  		    throw new RuntimeException("write peers list exception", e);
  	  	} finally {
    		objOutStream.close();
    	}
	}
	
	public static List<AgentData> readPeersList(String dir
			) throws IOException, RuntimeException{		
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
    	File file = new File(keyDir
    			, Constants.PEERS_FILE);
    	InputStream inputStream = new FileInputStream(file.toString());
  	  	ObjectInputStream objInputStream =
  	    new ObjectInputStream(new BufferedInputStream(inputStream));
  	  	try {
  	  		GetPeerListResponse getPeerListResponse 
  	  		= GetPeerListResponse.parseFrom(objInputStream); 	  	
	    	
	      	return getPeerListResponse.getKnownPeersList();
  	  	} catch (Exception e) {
  		    throw new RuntimeException("read peers list exception", e);
  	  	} finally {
  		  inputStream.close();
  	  	}
	}
	
	public static void writeSuperPeersList(String dir
			, List<AgentData> data) throws IOException, RuntimeException{
		ObjectOutputStream objOutStream = null;		
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
		keyDir.mkdirs();
    	File file = new File(keyDir
    			, Constants.SUPER_PEERS_FILE);
    	if(!file.exists()){
    		file.createNewFile();
    	}
    	
    	ResponseHeader responseHeader = ResponseHeader.newBuilder()
    	.setCurrentTestVersionNo(21)
    	.setCurrentVersionNo(21)
    	.build();
    	
    	GetSuperPeerListResponse getSuperPeerListResponse 
    	= GetSuperPeerListResponse.newBuilder()
    	.setHeader(responseHeader)
    	.addAllKnownSuperPeers(data)    	
    	.build();
    	    	
    	try {
			objOutStream = new ObjectOutputStream(
				    new BufferedOutputStream(new FileOutputStream(file)));									
			 getSuperPeerListResponse.writeTo(objOutStream);				 
				
    	} catch (Exception e) {
  		    throw new RuntimeException("write super peers list exception", e);
  	  	} finally {
    		objOutStream.close();
    	}
	}
	
	public static List<AgentData> readSuperPeersList(String dir
			) throws IOException, RuntimeException{		
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
    	File file = new File(keyDir
    			, Constants.SUPER_PEERS_FILE);
    	InputStream inputStream = new FileInputStream(file.toString());
  	  	ObjectInputStream objInputStream =
  	    new ObjectInputStream(new BufferedInputStream(inputStream));
  	  	try {
  	  		GetSuperPeerListResponse getSuperPeerListResponse 
  	  		= GetSuperPeerListResponse.parseFrom(objInputStream); 	  	
	    	
	      	return getSuperPeerListResponse.getKnownSuperPeersList();
  	  	} catch (Exception e) {
  		    throw new RuntimeException("read super peers list exception", e);
  	  	} finally {
  		  inputStream.close();
  	  	}
	}
	
	public static void writeEventsList(String dir
			, List<Event> data) throws IOException, RuntimeException{
		ObjectOutputStream objOutStream = null;		
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
		keyDir.mkdirs();
    	File file = new File(keyDir
    			, Constants.EVENTS_FILE);
    	if(!file.exists()){
    		file.createNewFile();
    	}
    	
    	ResponseHeader responseHeader = ResponseHeader.newBuilder()
    	.setCurrentTestVersionNo(21)
    	.setCurrentVersionNo(21)
    	.build();
    	
    	GetEventsResponse getEventsResponse 
    	= GetEventsResponse.newBuilder()
    	.addAllEvents(data)
    	.setHeader(responseHeader)
    	.build();
    	    	
    	try {
			objOutStream = new ObjectOutputStream(
				    new BufferedOutputStream(new FileOutputStream(file)));									
			 getEventsResponse.writeTo(objOutStream);				 
				
    	} catch (Exception e) {
  		    throw new RuntimeException("write events list exception", e);
  	  	} finally {
    		objOutStream.close();
    	}
	}
	
	public static List<Event> readEventsList(String dir
			) throws IOException, RuntimeException{		
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
    	File file = new File(keyDir
    			, Constants.EVENTS_FILE);
    	InputStream inputStream = new FileInputStream(file.toString());
  	  	ObjectInputStream objInputStream =
  	    new ObjectInputStream(new BufferedInputStream(inputStream));
  	  	try {
  	  		GetEventsResponse getEventsResponse
  	  		= GetEventsResponse.parseFrom(objInputStream); 	  	
	    	
	      	return getEventsResponse.getEventsList();
  	  	} catch (Exception e) {
  		    throw new RuntimeException("read events list exception", e);
  	  	} finally {
  		  inputStream.close();
  	  	}
	}
	
	public static void writeTestsList(String dir
			, List<Test> data) throws IOException, RuntimeException{
		ObjectOutputStream objOutStream = null;		
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
		keyDir.mkdirs();
    	File file = new File(keyDir
    			, Constants.TESTS_FILE);
    	if(!file.exists()){
    		file.createNewFile();
    	}
    	
    	ResponseHeader responseHeader = ResponseHeader.newBuilder()
    	.setCurrentTestVersionNo(21)
    	.setCurrentVersionNo(21)
    	.build();
    	
    	NewTestsResponse newTestsResponse = NewTestsResponse.newBuilder()
    	.addAllTests(data)
    	.setTestVersionNo(10)
    	.setHeader(responseHeader)
    	.build();
    	    	
    	try {
			objOutStream = new ObjectOutputStream(
				    new BufferedOutputStream(new FileOutputStream(file)));									
			 newTestsResponse.writeTo(objOutStream);				 
				
    	} catch (Exception e) {
  		    throw new RuntimeException("write tests list exception", e);
  	  	} finally {
    		objOutStream.close();
    	}
	}
	
	public static List<Test> readTestsList(String dir
			) throws IOException, RuntimeException{		
		sdCard = Environment.getExternalStorageDirectory();
		File keyDir = new File (sdCard.getAbsolutePath() 
    			+ dir);
    	File file = new File(keyDir
    			, Constants.TESTS_FILE);
    	InputStream inputStream = new FileInputStream(file.toString());
  	  	ObjectInputStream objInputStream =
  	    new ObjectInputStream(new BufferedInputStream(inputStream));
  	  	try {
  	  		NewTestsResponse newTestsResponse
  	  		= NewTestsResponse.parseFrom(objInputStream); 	  	
	    	
	      	return newTestsResponse.getTestsList();
  	  	} catch (Exception e) {
  		    throw new RuntimeException("read tests list exception", e);
  	  	} finally {
  		  inputStream.close();
  	  	}
	}
	
}