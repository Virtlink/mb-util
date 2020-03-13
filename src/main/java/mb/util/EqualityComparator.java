package mb.util;

import org.jetbrains.annotations.Nullable;

/**
 * Compares two objects for equality.
 *
 * This interface is contravariant.
 *
 * @param <T> the type of objects that this comparator can compare
 */
public interface EqualityComparator<T> {

    /**
     * Gets the default equality comparator that uses the object's implementations
     * of {@link Object#equals} and {@link Object#hashCode()}.
     */
    static <T> EqualityComparator<? super T> getDefault() {
        //noinspection unchecked
        return (EqualityComparator<? super T>)DefaultEqualityComparator.INSTANCE;
    }

    /**
     * Gets the identity equality comparator that uses the object's identity.
     */
    static <T> EqualityComparator<? super T> getIdentity() {
        //noinspection unchecked
        return (EqualityComparator<? super T>)IdentityEqualityComparator.INSTANCE;
    }


    /**
     * Compares two objects for equality.
     *
     * @param x the first object to compare; or {@code null}
     * @param y the second object to compare; or {@code null}
     * @return {@code true} when the objects are equal;
     * otherwise, {@code false}
     */
    boolean equals(@Nullable T x, @Nullable T y);

    /**
     * Computes the hash code of the specified object.
     *
     * @param obj the object for which to compute the hash code; or {@code null}
     * @return the computed hash code
     */
    int hashCodeOf(@Nullable T obj);

}
