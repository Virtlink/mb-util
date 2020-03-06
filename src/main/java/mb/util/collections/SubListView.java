package mb.util.collections;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ListIterator;

/**
 * An implementation of {@link ListView} that views part of a list.
 */
/* package private */ class SubListView<E> extends ListViewBase<E> implements Serializable {

    private final ListView<E> list;
    private final int offset;
    private final int length;

    /* package private */ SubListView(ListView<E> list, int offset, int length) {
        assert offset >= 0 && offset <= list.size();
        assert length >= 0 && offset + length <= list.size();
        this.list = list;
        this.offset = offset;
        this.length = length;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public E get(int index) {
        return list.get(offset + index);
    }

    @NotNull
    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > length)
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

    @Override
    public String toString() {
        return null;
    }

}
