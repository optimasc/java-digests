package com.optimasc.digest;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.MessageDigestSpi;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import com.optimasc.digest.CRC8Digest;
import com.optimasc.digest.DigestProvider;

import junit.framework.Assert;
import junit.framework.TestCase;


public class DigestTest extends TestCase
{
  /*  '123456789' */
  protected final byte VALUE_TINY[] =  {0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39};


  public static final String DATA_PATH = "res/";

  public static final String hashNames[] =
  {
    "CRC8",
    "CRC16CCITT",
    "Fletcher16",
    "CRC32C"
  };

  public static final String VALUE_TINY_HASHES[] =
    {
    /* CRC-8 */
    "F4",
    /* CRC-16 CCITT */
    "906E",
    /* FLETCHER-16 */
    "1EDE",
    /* CRC32C */
    "E3069283"
    };

  /* a.txt */
  public static final String FILE01_HASH_RESULT[] =
  {
    /* CRC-8 */
    "20",
    /* CRC-16 CCITT */
    "82F7",
    /* FLETCHER-16 */
    "6161",
    /* CRC32C */
    "C1D04330"
  };

  /* book1 */
  public static final String FILE02_HASH_RESULT[] =
  {
    /* CRC-8 */
    "89",
    /* CRC-16 CCITT */
    "EAC3",
    /* FLETCHER-16 */
    "E4F8",
    /* CRC32C */
    "336FF4C9"
  };


  /* world192.txt */
  public static final String FILE03_HASH_RESULT[] =
  {
    /* CRC-8 */
    "B7",
    /* CRC-16 CCITT */
    "E228",
    /* FLETCHER-16 */
    "0296",
    /* CRC32C */
    "C6928DB0"
  };

  protected void setUp() throws Exception
  {
    super.setUp();
    Security.addProvider(new DigestProvider());
  }

  protected void tearDown() throws Exception
  {
    super.tearDown();
  }

  final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
  public static String bytesToHex(byte[] bytes) {
      char[] hexChars = new char[bytes.length * 2];
      for ( int j = 0; j < bytes.length; j++ ) {
          int v = bytes[j] & 0xFF;
          hexChars[j * 2] = hexArray[v >>> 4];
          hexChars[j * 2 + 1] = hexArray[v & 0x0F];
      }
      return new String(hexChars);
  }

  public void testTiny() throws NoSuchAlgorithmException
  {
    int i;
    int j;
    for (i = 0; i < hashNames.length; i++)
    {
      String s = "";
      MessageDigest digest = MessageDigest.getInstance(hashNames[i]);

      //--- Go for the value as one entry.
      byte[] checksum = digest.digest(VALUE_TINY);
      s = bytesToHex(checksum);
      System.out.println("Checksum: ("+digest.getAlgorithm()+") :" + s);
      assertEquals(VALUE_TINY_HASHES[i],s);

      //--- Go for the value one byte per byte.
      digest.reset();
      for (j = 0; j < VALUE_TINY.length; j++)
      {
        digest.update(VALUE_TINY[j]);
      }
      checksum = digest.digest();
      s = bytesToHex(checksum);
      System.out.println("Checksum: ("+digest.getAlgorithm()+") :" + s);
      assertEquals(VALUE_TINY_HASHES[i],s);

    }
  }


  public void testFile01() throws NoSuchAlgorithmException, IOException
  {
    int i;
    int j;
    int size;
    int value;
    byte[] checksum;

    for (i = 0; i < hashNames.length; i++)
    {
      InputStream fileStream =DigestTest.class.getClassLoader().getResourceAsStream(DATA_PATH+"a.txt");
      String s = "";
      MessageDigest digest = MessageDigest.getInstance(hashNames[i]);

      //--- Go for the value one byte per byte.
      size = fileStream.available();
      digest.reset();
      for (j = 0; j < size; j++)
      {
        value = fileStream.read();
        digest.update((byte) (value & 0xFF));
      }
      checksum = digest.digest();
      s = bytesToHex(checksum);
      System.out.println("Checksum: ("+digest.getAlgorithm()+") :" + s);
      assertEquals(FILE01_HASH_RESULT[i],s);
      fileStream.close();

    }
  }

  public void testFile02() throws NoSuchAlgorithmException, IOException
  {
    int i;
    int j;
    int size;
    int value;
    byte[] checksum;

    for (i = 0; i < hashNames.length; i++)
    {
      InputStream fileStream =DigestTest.class.getClassLoader().getResourceAsStream(DATA_PATH+"book1");
      String s = "";
      MessageDigest digest = MessageDigest.getInstance(hashNames[i]);

      //--- Go for the value one byte per byte.
      size = fileStream.available();
      digest.reset();
      for (j = 0; j < size; j++)
      {
        value = fileStream.read();
        digest.update((byte) (value & 0xFF));
      }
      checksum = digest.digest();
      s = bytesToHex(checksum);
      System.out.println("Checksum: ("+digest.getAlgorithm()+") :" + s);
      assertEquals(FILE02_HASH_RESULT[i],s);
      fileStream.close();

    }
  }

  public void testFile03() throws NoSuchAlgorithmException, IOException
  {
    int i;
    int j;
    int size;
    int value;
    byte[] checksum;

    for (i = 0; i < hashNames.length; i++)
    {
      InputStream fileStream =DigestTest.class.getClassLoader().getResourceAsStream(DATA_PATH+"world192.txt");
      String s = "";
      MessageDigest digest = MessageDigest.getInstance(hashNames[i]);

      //--- Go for the value one byte per byte.
      size = fileStream.available();
      digest.reset();
      for (j = 0; j < size; j++)
      {
        value = fileStream.read();
        digest.update((byte) (value & 0xFF));
      }
      checksum = digest.digest();
      s = bytesToHex(checksum);
      System.out.println("Checksum: ("+digest.getAlgorithm()+") :" + s);
      assertEquals(FILE03_HASH_RESULT[i],s);
      fileStream.close();

    }
  }

}
