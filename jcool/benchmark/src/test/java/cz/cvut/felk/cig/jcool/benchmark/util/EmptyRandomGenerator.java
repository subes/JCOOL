package cz.cvut.felk.cig.jcool.benchmark.util;

import cz.cvut.felk.cig.jcool.core.RandomGenerator;

/**
 * Created by IntelliJ IDEA.
 * User: miklamar
 * Date: 23.2.2011
 * Time: 17:04
 * RandomGenerator providing empty implementation. Suitable for testing when ancestor overrides only necesary methods.
 */
public class EmptyRandomGenerator implements RandomGenerator{
    public void setSeed(int seed) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double nextRandom() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean nextBoolean() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean nextBoolean(double probability) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte nextByte() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte nextByte(byte maxExclusive) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte nextByte(byte minInclusive, byte maxExclusive) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int nextInt() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int nextInt(int maxExclusive) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int nextInt(int minInclusive, int maxExclusive) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long nextLong() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long nextLong(long maxExclusive) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long nextLong(long minInclusive, long maxExclusive) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public float nextFloat() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public float nextFloat(float maxExclusive) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public float nextFloat(float minInclusive, float maxExclusive) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double nextDouble() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double nextDouble(double maxExclusive) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double nextDouble(double minInclusive, double maxExclusive) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double nextGaussian() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double nextGaussian(double mean, double standardDeviation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
