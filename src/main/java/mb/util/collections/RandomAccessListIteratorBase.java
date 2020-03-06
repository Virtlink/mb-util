package mb.util.collections;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Base class for random access list iterator.
 *
 * This iterator maintains a cursor, the index of the current element,
 * and calls {@link #getElement(int)} to get the element at that position.
 */
/* package private */ abstract class RandomAccessListIteratorBase<E> implements ListIterator<E> {

    /**
     * The zero-based index of the element we're going to return on the subsequent call to next(),
     * and the element we returned on the past call to previous().
     */
    private int cursor;

    /**
     * Initializes a new instance of the {@link org.spoofax.terms.TermListIteratorBase} class.
     *
     * @param cursor the zero-based index of the element to return on the subsequent call to next()
     */
    protected RandomAccessListIteratorBase(int cursor) {
        this.cursor = cursor;
    }

    /**
     * Gets the size of the term.
     *
     * @return the number of subterms
     */
    protected abstract int getSize();

    /**
     * Gets the element at the specified index.
     *
     * This method is only called when the index is valid.
     *
     * @param index the zero-based index of the element to get
     * @return the element
     */
    protected abstract E getElement(int index);

    @Override
    public boolean hasNext() {
        // We don't need to check the lower bound
        return this.cursor < getSize();
    }

    @Override
    public E next() {
        if (!hasNext()) throw new NoSuchElementException();
        E subterm = getElement(this.cursor);
        this.cursor += 1;
        return subterm;
    }

    @Override
    public boolean hasPrevious() {
        // We don't need to check the upper bound
        return 0 < this.cursor;
    }

    @Override
    public E previous() {
        if (!hasPrevious()) throw new NoSuchElementException();
        this.cursor -= 1;
        return getElement(this.cursor);
    }

    @Override
    public int nextIndex() {
        return this.cursor;
    }

    @Override
    public int previousIndex() {
        return this.cursor - 1;
    }

    @Override
    @Deprecated
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void set(E iTerm) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void add(E iTerm) {
        throw new UnsupportedOperationException();
    }
}