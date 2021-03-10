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

    // The PersistentCollection has no of() or from() methods.
    // Use the of() and from() methods from the specific type of collection you want
    // (e.g., PersistentList, PersistentSet).

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
