package mb.util;

import org.jetbrains.annotations.Nullable;

/**
 * The default equality comparator implementation that uses the
 * object's own {@link Object#equals(Object)} and {@link Object#hashCode()}
 * methods.
 */
/* package private */ final class DefaultEqualityComparator<T> implements EqualityComparator<T> {

    public static final DefaultEqualityComparator<?> INSTANCE = new DefaultEqualityComparator<>();

    private DefaultEqualityComparator() {}

    @Override
    public boolean equals(@Nullable T x, @Nullable T y) {
        if (x == y) return true;    // Also captures x == null && y == null.
        if (x == null || y == null) return false;
        return x.equals(y);
    }

    @Override
    public int hashCodeOf(@Nullable T obj) {
        return obj != null ? obj.hashCode() : 0;
    }

}
