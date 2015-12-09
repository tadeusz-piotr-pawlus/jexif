package org.jexif.api.common;

import java.io.Serializable;
import java.math.BigInteger;

public class JExifTagNumber implements Serializable {
    private final int number;

    public JExifTagNumber(short number) {
        this(number, 10);
    }

    public JExifTagNumber(short number, int radix) {
        this("" + number, radix);
    }

    public JExifTagNumber(String number, int radix) {
        BigInteger n = new BigInteger(number, radix);
        if (n.intValue() < 0) {
            n = n.add(new BigInteger("10000", 16));
        }
        this.number = n.intValue();
    }

    public String toHexString() {
        return String.format("0x%s", Integer.toHexString(getNumber()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JExifTagNumber that = (JExifTagNumber) o;

        return number == that.number;

    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return toHexString();
    }

    public int getNumber() {
        return number;
    }
}
