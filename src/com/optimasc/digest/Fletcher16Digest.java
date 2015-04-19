/* Copyright (c) 2015 Optima SC Inc. All rights reserved.
 * 
 * See License.txt for more information on the license and usage terms of
 * this source code.
 *
 */
package com.optimasc.digest;

import java.security.MessageDigestSpi;

/** 
*
*  Fletcher-16 checksum hash algorithm. The hash algorithm was taken from
*  the wikipedia entry for Fletcer's checksum. The output size of the hash is
*  on 16-bits.
*
*  The identifier for this MessageDigest provider is "Fletcher16".
*
*  @author Carl Eric Codere
*
*/
public final class Fletcher16Digest extends MessageDigestSpi
{
  protected int s1 = 0;
  protected int s2 = 0;
  protected int hashValue = 0;

  protected void engineUpdate(byte input)
  {
    s1 = (s1 + input) % 255;
    s2 = (s2 + s1) % 255;
  }

  protected void engineUpdate(byte[] input, int offset, int len)
  {
    int _s1;
    int _s2;
    int index;
    _s1 = s1;
    _s2 = s2;
  
    for( index = 0; index < len; ++index )
    {
       _s1 = (_s1 + input[index+offset]) % 255;
       _s2 = (_s2 + _s1) % 255;
    }

    s1 = _s1;
    s2 = _s2;
  }

  protected byte[] engineDigest()
  {
    int hash;
    byte[] result = new byte[2];

    hash =  ((s2 << 8) | s1) & 0xFFFF;
    result[0] = (byte)((hash >> 8) & 0xFF);
    result[1] = (byte)((hash) & 0xFF);
    return result;
   }

  protected void engineReset()
  {
    s1 = 0;
    s2 = 0;
    hashValue = 0;
  }
  
  protected int engineGetDigestLength()
  {
    return 2;
  }
  

}
