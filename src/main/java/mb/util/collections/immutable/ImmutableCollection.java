package mb.util.collections.immutable;

import mb.util.collections.CollectionView;

/**
 * An immutable collection.
 *
 * This interface is covariant.
 *
 * Implementations of this interface are be immutable and thread-safe.
 *
 * @param <E> the type of elements in the collection
 */
public interface ImmutableCollection<E> extends CollectionView<E> {

    // The ImmutableCollection has no of() or from() methods.
    // Use the of() and from() methods from the specific type of collection you want
    // (e.g., ImmutableList, ImmutableSet).


//    /**
//     * Creates an empty immutable collection.
//     *
//     * @param <E> the type of elements in the collection
//     * @return the immutable collection
//     */
//    static <E> ImmutableCollection<E> of() {
//        return ImmutableList.of();
//    }
//
//    /**
//     * Creates a singleton immutable collection.
//     *
//     * @param element the element in the collection
//     * @param <E> the type of elements in the collection
//     * @return the immutable collection
//     */
//    static <E> ImmutableCollection<E> of(E element) {
//        return ImmutableList.of(element);
//    }
//
//    /**
//     * Creates an immutable collection from the specified array.
//     *
//     * Changes to the input array are reflected in this collection.
//     *
//     * @param elements the elements in the collection
//     * @param <E> the type of elements in the collection
//     * @return the immutable collection
//     */
//    @SafeVarargs static <E> ImmutableCollection<E> of(E... elements) {
//        return ImmutableList.of(elements);
//    }
//
//    /**
//     * Creates an immutable collection by wrapping the specified iterable.
//     *
//     * Changes to the input iterable are reflected in this collection.
//     *
//     * @param elements the elements to wrap
//     * @param <E> the type of elements in the collection
//     * @return the immutable collection
//     */
//    static <E> ImmutableCollection<E> from(Iterable<? extends E> elements) {
//        if (elements instanceof ImmutableCollection<?>) {
//            // When the collection is an immutable collection (and implements ImmutableCollection) we can just return it.
//            //noinspection unchecked
//            return (ImmutableCollection<E>)elements;
//        } else if (elements instanceof Collection<?>) {
//            // When the iterable is a collection, we call the other overload.
//            //noinspection unchecked
//            return from((Collection<E>)elements);
//        } else {
//            // Otherwise, we wrap the iterable in an immutable list.
//            return ImmutableList.from(elements);
//        }
//    }
//
//    /**
//     * Creates an immutable collection wrapping the specified collection.
//     *
//     * Changes to the input collection are reflected in this collection.
//     *
//     * @param collection the collection to wrap
//     * @param <E> the type of elements in the collection
//     * @return the immutable collection
//     */
//    static <E> ImmutableCollection<E> from(Collection<? extends E> collection) {
//        if (collection instanceof ImmutableCollection<?>) {
//            // When the collection is an immutable collection (and implements ImmutableCollection) we can just return it.
//            //noinspection unchecked
//            return (ImmutableCollection<E>)collection;
//        } else if (collection instanceof List<?>) {
//            // When the collection is a list, we wrap it in a ListView.
//            //noinspection unchecked
//            return ImmutableList.from((List<E>)collection);
//        } else if (collection instanceof Set<?>) {
//            // When the collection is a set, we wrap it in a SetView.
//            //noinspection unchecked
//            return ImmutableSet.from((Set<E>)collection);
//        } else {
//            // Otherwise, we wrap the collection in an immutable collection.
//            return new CollectionWrappingView<>(collection);
//        }
//    }


}
