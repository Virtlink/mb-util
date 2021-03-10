package mb.util.collections;

import mb.util.collections.immutable.ImmutableList;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;
import java.io.Serializable;
import java.util.*;

/**
 * An unmodifiable view of a list.
 *
 * This interface is covariant.
 *
 * Changes to the underlying list are visible through this view.
 * To get an immutable list, use one of the immutable interfaces.
 *
 * The implementation may not be thread-safe.
 * To get a thread-safe implementation, use one of the immutable interfaces.
 *
 * @param <E> the type of elements in the collection
 */
public interface ListView<E> extends CollectionView<E>, Serializable {

    /**
     * Creates an empty unmodifiable list.
     *
     * @param <E> the type of elements in the list
     * @return the unmodifiable list
     */
    static <E> ListView<E> of() {
        // We can return a special empty implementation.
        //noinspection unchecked
        return (ListView<E>)EmptyListView.INSTANCE;
//        return ImmutableList.of();
    }

    /**
     * Creates a singleton unmodifiable list.
     *
     * @param element the element in the list
     * @param <E> the type of elements in the list
     * @return the unmodifiable list
     */
    static <E> ListView<E> of(E element) {
        // We can return a special singleton implementation.
        return new ListSingletonView<>(element);
//        return ImmutableList.of(element);
    }

    /**
     * Creates an unmodifiable list from the specified array.
     *
     * Changes to the input array are reflected in this list.
     *
     * @param elements the elements in the list
     * @param <E> the type of elements in the list
     * @return the unmodifiable list
     */
    @SafeVarargs static <E> ListView<E> of(E... elements) {
        if (elements.length == 0) {
            // When the array is empty, we can just return the empty list,
            // because the input array cannot be modified and the fact that it
            // isn't part of the returned ListView is unobservable.
            return of();
        } else {
            // Otherwise, we wrap the array in a SetView.
            return new ListArrayView<>(elements);
        }
    }

    /**
     * Creates an unmodifiable list by wrapping the specified iterable.
     *
     * Changes to the input iterable are reflected in this list.
     *
     * @param elements the elements to wrap
     * @param <E> the type of elements in the list
     * @return the unmodifiable list
     */
    static <E> ListView<E> from(Iterable<? extends E> elements) {
        if (elements instanceof ListView<?>) {
            // When the iterable is an unmodifiable list (and implements ListView) we can just return it.
            //noinspection unchecked
            return (ListView<E>)elements;
        } else if (elements instanceof List<?>) {
            // When the iterable is a list, we call the other overload.
            //noinspection unchecked
            return from((List<E>)elements);
        } else {
            // Otherwise, we wrap the iterable in an unmodifiable list.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates an unmodifiable list wrapping the specified list.
     *
     * Changes to the input list are reflected in this list.
     *
     * @param list the list to wrap
     * @param <E> the type of elements in the list
     * @return the unmodifiable list
     */
    static <E> ListView<E> from(List<? extends E> list) {
        if (list instanceof ListView<?>) {
            // When the list is an unmodifiable list (and implements ListView) we can just return it.
            //noinspection unchecked
            return (ListView<E>)list;
        } else {
            // Otherwise, we wrap the set in an unmodifiable set.
            return new ListWrappingView<>(list);
        }
    }

    E get(int index);

    @Override default boolean contains(@Nullable Object element) {
        return indexOf(element) >= 0;
    }

    default int indexOf(@Nullable Object element) {
        // This implementation avoids allocating the iterator object,
        // but it would be more efficient to implement this with an Iterator (call `iterator()`)
        // when the term has inefficient random access (e.g., a Cons-Nil list term).

        for (int i = 0; i < this.size(); i++) {
            E e = get(i);
            if (Objects.equals(e, element)) return i;
        }
        return -1;
    }

    default int lastIndexOf(@Nullable Object element) {
        // This implementation avoids allocating the iterator object,
        // but it would be more efficient to implement this with a backward ListIterator (call `listIterator(size())`)
        // when the term has inefficient random access (e.g., a Cons-Nil list term).

        for (int i = this.size() - 1; i >= 0; i--) {
            E e = get(i);
            if (Objects.equals(e, element)) return i;
        }
        return -1;
    }

    @NotNull
    @Override
    default Iterator<E> iterator() {
        return listIterator();
    }

    default ListIterator<E> listIterator() {
        return listIterator(0);
    }

    default ListIterator<E> listIterator(int index) {
        return new RandomAccessListIteratorBase<E>(index) {
            @Override
            protected int getSize() {
                return size();
            }

            @Override
            protected E getElement(int index) {
                return get(index);
            }
        };
    }

    default <T> T[] toArray(T[] a) {
        int size = size();

        // It is more efficient to initialize and fill a correctly sized array than trying to fill the provided array.
        // Therefore, in this implementation, we always allocate a new array.
        // See also: https://stackoverflow.com/a/29444594/146622 and https://shipilev.net/blog/2016/arrays-wisdom-ancients/
        @SuppressWarnings("unchecked")
        T[] array = (T[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);

        // This implementation avoids allocating the iterator object,
        // but it would be more efficient to implement this with an Iterator
        // when the term has inefficient random access (e.g., a Cons-Nil list term).

        // When the implementation internally uses an array, the most efficient
        // is to do System.arraycopy, or Arrays.copyOf when we don't want to allocate our own array.

        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            T element = (T)get(i);
            array[i] = element;
        }

        return array;
    }


    default ListView<E> subListView(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > size() || toIndex < fromIndex || toIndex > size())
            throw new IndexOutOfBoundsException();

        return new SubListView<>(this, fromIndex, toIndex - fromIndex);
    }

    @Override List<E> asUnmodifiable();

}
