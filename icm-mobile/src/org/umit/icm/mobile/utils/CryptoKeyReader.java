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

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class CryptoKeyReader {
	
	public static PublicKey getMyPublicKey() throws IOException {
		return RSACrypto.readPublicKey(Constants.MY_PUBLIC_KEY_FILE);
	}
	
	public static PrivateKey getMyPrivateKey() throws IOException {
		return RSACrypto.readPrivateKey(Constants.MY_PRIVATE_KEY_FILE);
	}
	
	public static byte[] getMySecretKey() throws IOException {
		return AESCrypto.readKey(Constants.MY_SECRET_KEY_FILE);
	}
	
	public static byte[] getMyCipheredKey() throws IOException {
		return AESCrypto.readKey(Constants.MY_CIPHERED_KEY_FILE);
	}
	
	public static byte[] getPeerSecretKey(String peerIP) throws IOException {
		return AESCrypto.readKey(peerIP+Constants.PEER_SECRET_KEY_FILE);
	}
}