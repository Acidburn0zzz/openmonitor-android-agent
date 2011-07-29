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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.umit.icm.mobile.process.Constants;
import org.umit.icm.mobile.utils.SDCardReadWrite;

/**
 * This is the Service class. Provides getter/setters for Service
 */
public class Service implements Serializable {	
	/**
	 * Service serial UID
	 */
	private static final long serialVersionUID = -4063186487837165771L;
	private String name;
	private List<Integer> ports;
	private String status;
	private String check;	
	private String ip;
		
	public Service() {
		super();
		name = "";
		ports = new ArrayList<Integer>();
		status = "";
		check = "";
		ip = "";
	}		

	public Service(String name, List<Integer> ports, String ip, String status, String check) {
		super();
		this.name = name;
		this.ports = ports;
		this.status = status;
		this.check = check;
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getPorts() {
		return ports;
	}

	public void setPorts(List<Integer> ports) {
		this.ports = ports;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}		
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Writes the {@link Service} to disk using   
	                          
	{@link SDCardReadWrite#writeService} method.
	 * 
	 *	   	                          		                         	 	                      
	
	@see         SDCardReadWrite
	 */
	public void writeService() throws IOException {
		Service service 
		= new Service(this.name, this.ports, this.ip, this.status, this.check);
		SDCardReadWrite.writeService(Constants.SERVICES_DIR, service);
	}
	
	/**
	 * Returns a {@link Service} object read from disk using   
	                          
	{@link SDCardReadWrite#readService} method.
	 * 
	 *	
	
	@param  name  An object of the type String
	 *  	                          	
	                          
	@return      Service
	 *     	                          		                         	 	                      
	
	@see         SDCardReadWrite
	 */
	public Service readService(String name) throws IOException {
		return SDCardReadWrite.readService(Constants.SERVICES_DIR, name);
	}
	
	public boolean equals(Service service) {
		if(service.getCheck().equals(this.getCheck())
				&& service.getName().equals(this.getName())
				&& service.getIp().equals(this.getIp())
				&& service.getPorts().size() == this.getPorts().size()
				&& service.getStatus().equals(this.getStatus()))
			return true;
		return false;
	}
	
}