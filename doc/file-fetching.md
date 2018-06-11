Fetching the data files
=======================

Let `world` be the world number you are connected to.

1. Fetch content CRC file: `http://classic<world>.runescape.com/contentcrcs<N>`
    * Happens in the class `l`
    * Happens in the method `void a(URL uRL, vb vb2, byte by)`
2. Read in CRC values, which are 32-bit big endian integers
    * Happens in the class `wa`
    * Happens in the method `int c(int var1)`
3. For each CRC value `CRC_i`:
    1. Sign extend it to a 64-bit value and then print it as a hex string, excluding leading zeros
    2. Fetch content file: `http://classic<world>.runescape.com/content<i>_<CRC_i>`
    3. The CRC32 checksum of the content file should match `CRC_i`
    * Happens in the class `ub`
    * Happens in the method `byte[] a(int n, int n2, String string, int n3)`

**TODO:** Figure out how to unpack the content files

**TODO:** Determine how `N` is calculated. It is computed by: `db.a((int)0)`
