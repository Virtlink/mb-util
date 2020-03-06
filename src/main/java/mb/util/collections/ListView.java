package mb.util.collections;

import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;
import java.io.Serializable;
import java.util.*;

/**
 * An immutable view of a list.
 */
public interface ListView<E> extends CollectionView<E>, RandomAccess, Serializable {

    /**
     * Creates an empty unmodifiable collection.
     *
     * @param <E> the type of elements in the collection
     * @return the unmodifiable collection
     */
    static <E> ListView<E> of() {
        // This overload of of() always returns the same empty ListView instance.
        //noinspection unchecked
        return (ListView<E>)EmptyListView.INSTANCE;
    }

    /**
     * Creates a singleton unmodifiable collection.
     *
     * @param element the element in the collection
     * @param <E> the type of elements in the collection
     * @return the unmodifiable collection
     */
    static <E> ListView<E> of(E element) {
        // This overload of of() returns a singleton ListView instance.
        return new ListSingletonView<>(element);
    }

    /**
     * Creates an unmodifiable collection from the specified array.
     *
     * @param elements the elements in the collection
     * @param <E> the type of elements in the collection
     * @return the unmodifiable collection
     */
    @SafeVarargs static <E> ListView<E> of(E... elements) {
        return new ListArrayView<>(elements);
    }

    /**
     * Creates an unmodifiable collection wrapping the specified collection.
     *
     * @param collection the collection to wrap
     * @param <E> the type of elements in the collection
     * @return the unmodifiable collection
     */
    static <E> ListView<E> from(List<? extends E> collection) {
        return new ListWrappingView<>(collection);
    }

    /**
     * Creates an unmodifiable collection wrapping a copy of the specified elements.
     *
     * @param elements the elements to copy
     * @param <E> the type of elements in the collection
     * @return the unmodifiable collection
     */
    static <E> ListView<E> copyFrom(Iterable<? extends E> elements) {
        final ArrayList<E> list = new ArrayList<>();
        for (E element : elements) {
            list.add(element);
        }
        return new ListWrappingView<>(list);
    }

    /**
     * Creates an unmodifiable collection wrapping a copy of the specified collection.
     *
     * @param collection the collection to copy
     * @param <E> the type of elements in the collection
     * @return the unmodifiable collection
     */
    static <E> CollectionView<E> copyFrom(Collection<? extends E> collection) {
        return new CollectionWrappingView<>(new ArrayList<>(collection));
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


    ListView<E> subListView(int fromIndex, int toIndex);

    @Override List<E> asUnmodifiable();

}
