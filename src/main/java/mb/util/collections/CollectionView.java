package mb.util.collections;

import mb.util.EqualityComparator;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * An unmodifiable view of a collection.
 *
 * This interface is covariant.
 *
 * Changes to the underlying collection are visible through this view.
 * To get an immutable collection, use one of the immutable interfaces.
 *
 * The implementation may not be thread-safe.
 * To get a thread-safe implementation, use one of the immutable interfaces.
 *
 * @param <E> the type of elements in the collection
 */
public interface CollectionView<E> extends Iterable<E>, Serializable {

//    /**
//     * Creates an empty unmodifiable collection.
//     *
//     * @param <E> the type of elements in the collection
//     * @return the unmodifiable collection
//     */
//    static <E> CollectionView<E> of() {
//        return ListView.of();
//    }
//
//    /**
//     * Creates a singleton unmodifiable collection.
//     *
//     * @param element the element in the collection
//     * @param <E> the type of elements in the collection
//     * @return the unmodifiable collection
//     */
//    static <E> CollectionView<E> of(E element) {
//        return ListView.of(element);
//    }
//
//    /**
//     * Creates an unmodifiable collection from the specified array.
//     *
//     * Changes to the input array are reflected in this collection.
//     *
//     * @param elements the elements in the collection
//     * @param <E> the type of elements in the collection
//     * @return the unmodifiable collection
//     */
//    @SafeVarargs static <E> CollectionView<E> of(E... elements) {
//        return ListView.of(elements);
//    }
//
//    /**
//     * Creates an unmodifiable collection by wrapping the specified iterable.
//     *
//     * Changes to the input iterable are reflected in this collection.
//     *
//     * @param elements the elements to wrap
//     * @param <E> the type of elements in the collection
//     * @return the unmodifiable collection
//     */
//    static <E> CollectionView<E> from(Iterable<? extends E> elements) {
//        if (elements instanceof CollectionView<?>) {
//            // When the collection is an unmodifiable collection (and implements CollectionView) we can just return it.
//            //noinspection unchecked
//            return (CollectionView<E>)elements;
//        } else if (elements instanceof Collection<?>) {
//            // When the iterable is a collection, we call the other overload.
//            //noinspection unchecked
//            return from((Collection<E>)elements);
//        } else {
//            // Otherwise, we wrap the iterable in an unmodifiable list.
//            return ListView.from(elements);
//        }
//    }
//
//    /**
//     * Creates an unmodifiable collection wrapping the specified collection.
//     *
//     * Changes to the input collection are reflected in this collection.
//     *
//     * @param collection the collection to wrap
//     * @param <E> the type of elements in the collection
//     * @return the unmodifiable collection
//     */
//    static <E> CollectionView<E> from(Collection<? extends E> collection) {
//        if (collection instanceof CollectionView<?>) {
//            // When the collection is an unmodifiable collection (and implements CollectionView) we can just return it.
//            //noinspection unchecked
//            return (CollectionView<E>)collection;
//        } else if (collection instanceof List<?>) {
//            // When the collection is a list, we wrap it in a ListView.
//            //noinspection unchecked
//            return ListView.from((List<E>)collection);
//        } else if (collection instanceof Set<?>) {
//            // When the collection is a set, we wrap it in a SetView.
//            //noinspection unchecked
//            return SetView.from((Set<E>)collection);
//        } else {
//            // Otherwise, we wrap the collection in an unmodifiable collection.
//            return new CollectionWrappingView<>(collection);
//        }
//    }

    /**
     * Gets the size of the collection.
     *
     * @return the size of the collection
     */
    int size();

    /**
     * Gets whether the collection is empty.
     *
     * @return {@code true} when the collection is empty; otherwise, {@code false}
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Gets the equality comparator used to compare the objects in this collection.
     *
     * @return the equality comparator
     */
    default EqualityComparator<? super E> getComparator() {
        // Most implementations do not support custom equality comparators,
        // and just use the equals() and hashCode() implementations of the objects by default.
        return EqualityComparator.getDefault();
    }

    /**
     * Determines whether the collection contains the specified element.
     *
     * @param element the element to check
     * @return {@code true} when the collection contains the specified element;
     * otherwise, {@code false}
     */
    // Accepting Object instead of E is consistent with the Collection<?> interface.
    // It also allows comparison to an object whose static type is not known.
    // Additionally, it allows this interface to be covariant in E, as it has no methods
    // with a parameter of type E. Finally, this method will just return false when the
    // given object is of the wrong type, because then it clearly is not in the collection.
    boolean contains(Object element);

    /**
     * Determines whether the collection contains all the specified elements.
     *
     * @param elements the elements to check
     * @return {@code true} when the collection contains all the specified elements;
     * otherwise, {@code false}
     */
    default boolean containsAll(Collection<?> elements) {
        // Accepting Collection<?> instead of Collection<E> or Collection<? extends E>
        // is consistent with the Collection<?> interface and the contains(Object) method.
        for (Object element : elements) {
            if (!contains(element)) return false;
        }
        return true;
    }

    /**
     * Adds all elements from this collection to the specified collection.
     *
     * @param collection the collection to add all elements to
     */
    default void addAllTo(Collection<? super E> collection) {
        // Accepting a Collection<? super E> allows us to add elements to
        // any collection that can hold E elements, including Collection<Object>.
        for (E element : this) {
            collection.add(element);
        }
    }

    /**
     * Creates a serial stream from this collection.
     *
     * @return the created stream
     */
    default Stream<E> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    /**
     * Creates a parallel stream from this collection.
     *
     * @return the created stream
     */
    default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    /**
     * Returns this collection as an unmodificable object implementing {@link Collection}.
     *
     * @return the unmmodifiable collection
     */
    Collection<E> asUnmodifiable();

    /**
     * Copies all elements from this collection to a new array.
     *
     * @return the new array with all the elements
     */
    default Object[] toArray() {
        return toArray(new Object[0]);
    }

    /**
     * Copies all elements from this collection to a new array.
     *
     * @param a an array indicating the type of elements in the array
     * @param <T> the type of elements in the array
     * @return the new array with all the elements
     */
    default <T> T[] toArray(T[] a) {
        int size = size();

        // It is more efficient to call this method with an empty array of the correct type.
        // See also: https://stackoverflow.com/a/29444594/146622 and https://shipilev.net/blog/2016/arrays-wisdom-ancients/
        @SuppressWarnings("unchecked")
        T[] array = a.length != size ? (T[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size) : a;

        // This implementation allocates the iterator object,
        // since collections, in general, do not have random access.

        Iterator<E> iterator = this.iterator();
        int i = 0;
        // Copy all the elements from the iterator to the array we allocated
        while (iterator.hasNext() && i < size) {
            @SuppressWarnings("unchecked")
            T element = (T)iterator.next();
            array[i] = element;
            i += 1;
        }
        if (i < size) {
            // Somehow we got less elements from the iterator than expected.
            // Null-terminate the array (seems to be good practice).
            array[i] = null;
            // Copy the interesting part of the array and return it.
            return Arrays.copyOf(array, i);
        }
        else if (iterator.hasNext()) {
            // Somehow there are more elements in the iterator than expected.
            // We'll use an ArrayList for the rest.
            List<T> list = Arrays.asList(array);
            while (iterator.hasNext()) {
                @SuppressWarnings("unchecked")
                T element = (T)iterator.next();
                list.add(element);
            }
            // Here we know the array was too small, so it doesn't matter what we pass.
            return list.toArray(a);
        } else {
            // Happy path: the array's size was just right to get all the elements from the iterator.
            return array;
        }
    }

}
