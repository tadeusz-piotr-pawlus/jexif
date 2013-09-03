package org.jexif.api.common;

import java.io.Serializable;
import java.math.BigInteger;

public class JExifTagNumber implements Serializable {
    private final int number;

    public JExifTagNumber(short number, int radix) {
        this.number = new BigInteger("" + number, radix).intValue();
    }

    public JExifTagNumber(String number, int radix) {
        this.number = new BigInteger(number, radix).intValue();
    }

    public String toHexString() {
        return Integer.toHexString(getNumber());
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
