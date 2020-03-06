package mb.util.collections.immutable;

import mb.util.collections.mutable.MutableCollection;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * A persistent collection.
 *
 * @param <E> the type of elements in the collection
 */
public interface PersistentCollection<E> extends ImmutableCollection<E> {

    /**
     * Creates an empty persistent collection.
     *
     * @param <E> the type of elements in the collection
     * @return the persistent collection
     */
    static <E> PersistentCollection<E> of() {
        // This overload of of() always returns the same empty ListView instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a singleton persistent collection.
     *
     * @param element the element in the collection
     * @param <E> the type of elements in the collection
     * @return the persistent collection
     */
    static <E> PersistentCollection<E> of(E element) {
        // This overload of of() returns a singleton ListView instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an persistent collection from the specified array.
     *
     * @param elements the elements in the collection
     * @param <E> the type of elements in the collection
     * @return the persistent collection
     */
    @SafeVarargs static <E> PersistentCollection<E> of(E... elements) {
        if (elements.length == 0) return of();
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an persistent collection wrapping the specified collection.
     *
     * @param collection the collection to wrap
     * @param <E> the type of elements in the collection
     * @return the persistent collection
     */
    static <E> PersistentCollection<E> from(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an persistent collection wrapping a copy of the specified elements.
     *
     * @param elements the elements to copy
     * @param <E> the type of elements in the collection
     * @return the persistent collection
     */
    static <E> PersistentCollection<E> copyFrom(Iterable<? extends E> elements) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an persistent collection wrapping a copy of the specified collection.
     *
     * @param collection the collection to copy
     * @param <E> the type of elements in the collection
     * @return the persistent collection
     */
    static <E> PersistentCollection<E> copyFrom(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    PersistentCollection<E> add(E element);
    PersistentCollection<E> addAll(Iterable<? extends E> elements);
    PersistentCollection<E> remove(E element);
    PersistentCollection<E> removeAll(Iterable<? extends E> elements);
    PersistentCollection<E> retainAll(Iterable<? extends E> elements);
    PersistentCollection<E> removeAllWhere(Predicate<E> predicate);
    PersistentCollection<E> retainAllWhere(Predicate<E> predicate);
    PersistentCollection<E> replaceAll(UnaryOperator<E> operator);
    PersistentCollection<E> clear();

    interface Builder<E> extends MutableCollection<E> {
        PersistentCollection<E> build();
    }

}
