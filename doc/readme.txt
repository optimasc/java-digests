Information
===========
This package contains hash/checksum algorithms to be used as JCE Digest
crypto providers. 

To use, add the following in your code:

    Security.addProvider(new DigestProvider());  
    
    
The algorithms implemented are as follows:

CRC-8 "CRC8"
CRC32C "CRC32C"  
CRC-16 X.25 "CRC16CCITT" 
Fletcher-16 "FLETCHER16" 

Enjoy!
March 2015
Carl Eric Codere (info@optimasc.com)


