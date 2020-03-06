package mb.util.collections;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ListIterator;

/**
 * An implementation of {@link ListView} that wraps an array.
 */
/* package private */ class ListArrayView<E> extends ListViewBase<E> implements Serializable {

    private final E[] array;

    /* package private */ ListArrayView(E[] array) {
        this.array = array;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public E get(int index) {
        return array[index];
    }

    @NotNull
    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > array.length)
            throw new IndexOutOfBoundsException();

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

}
