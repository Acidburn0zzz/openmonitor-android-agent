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

package org.umit.icm.mobile.test.utils;



import java.util.ArrayList;
import java.util.List;

import org.umit.icm.mobile.process.Constants;
import org.umit.icm.mobile.proto.MessageProtos.AgentData;
import org.umit.icm.mobile.proto.MessageProtos.Event;
import org.umit.icm.mobile.proto.MessageProtos.Test;
import org.umit.icm.mobile.utils.SDCardReadWrite;

import junit.framework.Assert;
import android.test.AndroidTestCase;


public class ParametersReadWriteTests extends AndroidTestCase {

    public void testPeersList() throws Throwable {
    	AgentData agent1 = AgentData.newBuilder()
    	.setAgentIP("IP1")
    	.setAgentID("ID1")
    	.setAgentPort(11)
    	.setPeerStatus("On")
    	.setPublicKey("publicKey1")
    	.setToken("token1")
    	.build();
    	
    	AgentData agent2 = AgentData.newBuilder()
    	.setAgentIP("IP2")
    	.setAgentID("ID2")
    	.setAgentPort(12)
    	.setPeerStatus("On")
    	.setPublicKey("publicKey2")
    	.setToken("token2")
    	.build();
    	
    	List<AgentData> peerList = new ArrayList<AgentData>();
    	peerList.add(agent1);
    	peerList.add(agent2);
        
    	SDCardReadWrite.writePeersList(Constants.PARAMETERS_DIR, peerList);
    	
    	List<AgentData> peerList2 
    	= SDCardReadWrite.readPeersList(Constants.PARAMETERS_DIR);
    	
    	Assert.assertEquals(peerList, peerList2);
    }        
    
    public void testSuperPeersList() throws Throwable {
    	AgentData agent1 = AgentData.newBuilder()
    	.setAgentIP("IP1")
    	.setAgentID("ID1")
    	.setAgentPort(11)
    	.setPeerStatus("On")
    	.setPublicKey("publicKey1")
    	.setToken("token1")
    	.build();
    	
    	AgentData agent2 = AgentData.newBuilder()
    	.setAgentIP("IP2")
    	.setAgentID("ID2")
    	.setAgentPort(12)
    	.setPeerStatus("On")
    	.setPublicKey("publicKey2")
    	.setToken("token2")
    	.build();
    	
    	List<AgentData> peerList = new ArrayList<AgentData>();
    	peerList.add(agent1);
    	peerList.add(agent2);
        
    	SDCardReadWrite.writeSuperPeersList(Constants.PARAMETERS_DIR, peerList);
    	
    	List<AgentData> peerList2 
    	= SDCardReadWrite.readSuperPeersList(Constants.PARAMETERS_DIR);
    	
    	Assert.assertEquals(peerList, peerList2);
    }  
    
    public void testEventsList() throws Throwable {
    	
    	Event event1 = Event.newBuilder()
    	.setEventType("CENSOR")
    	.addLocations("Islamabad")
    	.setSinceTimeUTC(100)
    	.setTimeUTC(1000)
    	.setTestType("SERVICE")
    	.build();
    	
    	Event event2 = Event.newBuilder()
    	.setEventType("OFF_LINE")
    	.addLocations("Islamabad2")
    	.setSinceTimeUTC(101)
    	.setTimeUTC(1001)
    	.setTestType("WEB")
    	.build();
    	
    	List<Event> eventList = new ArrayList<Event>();
    	eventList.add(event1);
    	eventList.add(event2);
        
    	SDCardReadWrite.writeEventsList(Constants.PARAMETERS_DIR, eventList);
    	
    	List<Event> eventList2 
    	= SDCardReadWrite.readEventsList(Constants.PARAMETERS_DIR);
    	
    	Assert.assertEquals(eventList, eventList2);
    } 
    
public void testTestsList() throws Throwable {
    	
		Test test1 = Test.newBuilder()
		.setExecuteAtTimeUTC(11)
		.setServideCode(21)
		.setTestID(31)
		.setWebsiteURL("url1")
		.setTestType("WEB")
		.build();
		
		Test test2 = Test.newBuilder()
		.setExecuteAtTimeUTC(12)
		.setServideCode(22)
		.setTestID(32)
		.setWebsiteURL("url2")
		.setTestType("SERVICE")
		.build();
    	
    	List<Test> testList = new ArrayList<Test>();
    	testList.add(test1);
    	testList.add(test2);
        
    	SDCardReadWrite.writeTestsList(Constants.TESTS_DIR, testList);
    	
    	List<Test> testList2 
    	= SDCardReadWrite.readTestsList(Constants.TESTS_DIR);
    	
    	Assert.assertEquals(testList, testList2);
    } 

}