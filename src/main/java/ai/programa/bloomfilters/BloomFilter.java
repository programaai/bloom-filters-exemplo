package ai.programa.bloomfilters;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.stream.IntStream;

public class BloomFilter {
  private static final double LN2 = Math.log(2);

  private final BitSet bitArray;
  private final int hashCount;

  public BloomFilter(int elementCount, double errorRate) {
    int capacity = getOptimalCapacity(elementCount, errorRate);
    this.hashCount = getHashCount(elementCount, capacity);
    this.bitArray = new BitSet(capacity);
  }

  public void add(String item) {
    add(item, bitArray);
  }

  public boolean exists(String item) {
    final BitSet bitSet = new BitSet(bitArray.size());
    add(item, bitSet);
    final BitSet resultado = (BitSet) bitArray.clone();
    resultado.and(bitSet);
    return !resultado.isEmpty();
  }

  private void add(String item, BitSet bitSet) {
    final int[] indices = getIndices(item);

    for (final int index : indices) {
      bitSet.set(index);
    }
  }

  private int[] getIndices(String item) {
    return IntStream.range(0, hashCount)
        .map(i -> getHashN(i, item))
        .toArray();
  }

  private int getHashN(int i, String item) {
    //noinspection UnstableApiUsage
    return Math.abs(Hashing.murmur3_32(i)
        .hashString(item, StandardCharsets.UTF_8)
        .asInt()) % bitArray.size();
  }

  private int getHashCount(final double elementCount, final int capacity) {
    return (int) Math.round((capacity / elementCount) * LN2);
  }

  private int getOptimalCapacity(final int elementCount, final double errorRate) {
    return (int) Math
        .ceil((elementCount * Math.log(errorRate)) / Math.log(1 / Math.pow(2, LN2)));
  }
}
