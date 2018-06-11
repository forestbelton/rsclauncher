Content file format
=====================

Content files are archive files containing many pieces of packed data inside of them. They are split into two parts: the file entry table and the packed file data. In order to unpack a file, it is necessary to walk the file entry table until a matching hash is found. Once located, the offset into the content file is available and can be seeked to directly.

File entry table
----------------

Multi-byte values are packed with a big endian representation. The first two bytes of the table represent the total number of entries in the table. Each entry is 10 bytes long and contains the following:

|Name|Length|Description|
|----|------|-----------|
|fileHash|4|4-byte hash of the file name. Hash function detailed below|
|fileSize|3|Size of the file, uncompressed|
|fileSizeCompressed|3|Size of the file, compressed|

If `fileSize > fileSizeCompressed`, then the file is compressed using [bzip2](https://en.wikipedia.org/wiki/Bzip2).
 
Hash function
-------------

The hash function is given by the following pseudocode:

```
hash(s) {
  val = 0
  
  for (i = 0; i < s.length; ++i) {
    val = (val * 61 + s[i]) - 32
  }
  
  return val
}
```