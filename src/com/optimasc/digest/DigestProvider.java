/* Copyright (c) 2015 Optima SC Inc. All rights reserved.
 * 
 * See License.txt for more information on the license and usage terms of
 * this source code.
 *
 */
package com.optimasc.digest;

import java.security.Provider;

/** Implements and registers the Optima SC digest provider. 
 * */
public class DigestProvider extends Provider
{
  private static final long serialVersionUID = -67123468605911408L;  
  
  public DigestProvider() {  
   super("SimpleDiest", 1.0, "Checksum provider v1.0");  
   
   put("MessageDigest.CRC16CCITT", CRC16CCITTDigest.class.getName());  
   put("MessageDigest.CRC8", CRC8Digest.class.getName());  
   put("MessageDigest.Fletcher16", Fletcher16Digest.class.getName());  
   put("MessageDigest.CRC32C", CRC32CDigest.class.getName());
  }
}
