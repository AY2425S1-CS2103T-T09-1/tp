package seedu.address.model.person;


import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Remark {

    public final String value;

    /**
     * Constructs an {@link Remark}.
     *
     * @param remark A remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return Objects.equals(value, otherRemark.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
