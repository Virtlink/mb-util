package mb.util.collections

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.opentest4j.TestAbortedException

/** Tests the [MutableIterable] interface. */
@DisplayName("MutableIterable")
interface MutableIterableTests {

    /** Tests the [MutableIterable.iterator] property. */
    @DisplayName("iterator()")
    interface IteratorTests {

        fun <E> create(vararg elements: E): MutableIterable<E>

        @Test
        fun `throws exception when removing from empty iterable`() {
            val iterator = ifNotNull(create<String>()).iterator()

            assertThrows<NoSuchElementException> { iterator.remove() }
        }

        @Test
        fun `removes single element when iterable is singleton`() {
            val iterator = ifNotNull(create("A")).iterator()

            iterator.remove()

            assertThrows<NoSuchElementException> { iterator.remove() }
        }

        @Test
        fun `removes each element when iterable has multiple elements`() {
            val iterator = ifNotNull(create("A", "B", "C")).iterator()

            iterator.remove()
            iterator.remove()
            iterator.remove()

            assertThrows<NoSuchElementException> { iterator.remove() }
        }

        @Test
        fun `throws exception when modifying the collection while iterating`() {
            val iterable = ifNotNull(create("A", "B", "C"))
            
            val iterator1 = iterable.iterator()
            val iterator2 = iterable.iterator()

            iterator1.next()
            iterator2.remove()

            assertThrows<ConcurrentModificationException> { iterator1.next() }
        }

    }


}
