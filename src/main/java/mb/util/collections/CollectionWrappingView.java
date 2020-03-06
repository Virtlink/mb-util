package mb.util.collections;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Collection view that wraps a collection.
 *
 * Most operations are delegated to the wrapped collection.
 *
 * @param <E> the type of elements in the collection
 */
/* package private */ class CollectionWrappingView<E> extends CollectionViewBase<E> implements Serializable {

    /** The wrapped collection. */
    protected final Collection<? extends E> collection;

    /* package private */ CollectionWrappingView(Collection<? extends E> collection) {
        this.collection = collection;
    }

    @Override
    public void addAllTo(Collection<? super E> collection) {
        collection.addAll(this.collection);
    }

    // Collection<T>

    @Override
    public int size() {
        return this.collection.size();
    }

    @Override
    public boolean isEmpty() {
        return this.collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.collection.contains(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> elements) {
        return this.collection.containsAll(elements);
    }

    @Override
    public Stream<E> stream() {
        //noinspection unchecked
        return (Stream<E>)this.collection.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        //noinspection unchecked
        return (Stream<E>)this.collection.parallelStream();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return this.collection.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        //noinspection SuspiciousToArrayCall
        return this.collection.toArray(a);
    }

    // Iterable<T>

    @NotNull
    @Override
    public Iterator<E> iterator() {
        // TODO: Wrap the iterator to disallow modifications
        //noinspection unchecked
        return (Iterator<E>)this.collection.iterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        this.collection.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        // TODO: Wrap the spliterator to disallow modifications
        //noinspection unchecked
        return (Spliterator<E>)this.collection.spliterator();
    }

    // Object

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof CollectionWrappingView<?>) {
            // Happy path
            return this.collection.equals(((CollectionWrappingView<?>)obj).collection);
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        return this.collection.hashCode();
    }

    @Override
    public String toString() {
        return this.collection.toString();
    }

    /**
     * A wrapping iterator that disallows modifications.
     *
     * @param <E> the type of elements
     */
    static class WrappingIterator<E> implements Iterator<E> {

        private final Iterator<E> wrappedIterator;

        WrappingIterator(Iterator<E> wrappedIterator) {
            this.wrappedIterator = wrappedIterator;
        }

        @Override
        public boolean hasNext() {
            return this.wrappedIterator.hasNext();
        }

        @Override
        public E next() {
            return this.wrappedIterator.next();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            this.wrappedIterator.forEachRemaining(action);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
