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

package org.umit.icm.mobile.test.p2p;

import junit.framework.Assert;

import org.umit.icm.mobile.p2p.MessageQueue;
import org.umit.icm.mobile.p2p.QueueObject;
import org.umit.icm.mobile.proto.MessageProtos.AgentData;
import org.umit.icm.mobile.proto.MessageProtos.RSAKey;

import android.test.AndroidTestCase;

public class MessageQTests extends AndroidTestCase {
	
	public void testMessageQ() throws Throwable {
		RSAKey rsaKey1 = RSAKey.newBuilder()
		.setExp("exp1")
		.setMod("mod")
		.build();
		
		AgentData agent1 = AgentData.newBuilder()
		.setAgentIP("IP1")
		.setAgentID("1")
		.setAgentPort(11)
		.setPeerStatus("On")
		.setPublicKey(rsaKey1)
		.setToken("token1")
		.build();
		
		RSAKey rsaKey2 = RSAKey.newBuilder()
		.setExp("exp2")
		.setMod("mosd2")
		.build();
		AgentData agent2 = AgentData.newBuilder()
		.setAgentIP("IP2")
		.setAgentID("2")
		.setAgentPort(12)
		.setPeerStatus("On")
		.setPublicKey(rsaKey2)
		.setToken("token2")
		.build();
		
		RSAKey rsaKey3 = RSAKey.newBuilder()
		.setExp("exp3")
		.setMod("mod3")
		.build();
		AgentData agent3 = AgentData.newBuilder()
		.setAgentIP("IP3")
		.setAgentID("3")
		.setAgentPort(13)
		.setPeerStatus("On")
		.setPublicKey(rsaKey3)
		.setToken("token3")
		.build();
		
		QueueObject obj1 = new QueueObject(agent1, "what".getBytes(), 1);
		QueueObject obj2 = new QueueObject(agent2, "what".getBytes(), 2);
		QueueObject obj3 = new QueueObject(agent3, "what".getBytes(), 3);
		
		MessageQueue messageQ = new MessageQueue();
		
		messageQ.addMessage(obj1);
		messageQ.addMessage(obj2);
		messageQ.addMessage(obj3);
		
		Assert.assertEquals(messageQ.removeMessage(), obj1);
		Assert.assertEquals(messageQ.removeMessage(), obj2);
		Assert.assertEquals(messageQ.removeMessage(), obj3);
	}
		
}