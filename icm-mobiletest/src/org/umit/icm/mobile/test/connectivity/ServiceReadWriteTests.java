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

package org.umit.icm.mobile.test.connectivity;



import java.util.ArrayList;
import java.util.List;

import org.umit.icm.mobile.connectivity.Service;

import junit.framework.Assert;
import android.test.AndroidTestCase;


public class ServiceReadWriteTests extends AndroidTestCase {

    public void testServiceReadWrite() throws Throwable {
    	List<Integer> ports = new ArrayList<Integer>();
    	ports.add(8080);
    	ports.add(8000);
    	Service service = new Service("name", ports, "ip", "status", "check");
    	service.writeService();
    	Service newService 
    	= service.readService(service.getName());
        Assert.assertTrue(service.equals(newService));
    }        
       
}