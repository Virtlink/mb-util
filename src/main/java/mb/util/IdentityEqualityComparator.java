package mb.util;

import org.jetbrains.annotations.Nullable;

/**
 * The identity equality comparator implementation that uses
 * object identity to compare objects.
 */
/* package private */ final class IdentityEqualityComparator<T> implements EqualityComparator<T> {

    public static final IdentityEqualityComparator<?> INSTANCE = new IdentityEqualityComparator<>();

    private IdentityEqualityComparator() {}

    @Override
    public boolean compare(@Nullable T x, @Nullable T y) {
        return x == y;  // Also captures x == null && y == null.
    }

    @Override
    public int hashCodeOf(@Nullable T obj) {
        return System.identityHashCode(obj);
    }

}
