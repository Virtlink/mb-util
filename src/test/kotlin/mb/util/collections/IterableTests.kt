package mb.util.collections

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.opentest4j.TestAbortedException

/** Tests the [Iterable] interface. */
@DisplayName("Iterable")
interface IterableTests {

    /** Tests the [Iterable.iterator] property. */
    @DisplayName("iterator()")
    interface IteratorTests {

        fun <E> create(vararg elements: E): Iterable<E>

        @Test
        fun `yields nothing when iterable is empty`() {
            val iterator = create<String>().iterator()

            // Multiple calls should return the same result
            assertFalse(iterator.hasNext())
            assertFalse(iterator.hasNext())
            assertFalse(iterator.hasNext())

            assertThrows<NoSuchElementException> { iterator.next() }

            assertFalse(iterator.hasNext())
        }

        @Test
        fun `yields single element when iterable is singleton`() {
            val element = "A"
            val iterator = create(element).iterator()

            // Multiple calls should return the same result
            assertTrue(iterator.hasNext())
            assertTrue(iterator.hasNext())
            assertTrue(iterator.hasNext())

            assertSame(element, iterator.next())

            // Multiple calls should return the same result
            assertFalse(iterator.hasNext())
            assertFalse(iterator.hasNext())
            assertFalse(iterator.hasNext())

            assertThrows<NoSuchElementException> { iterator.next() }
        }

        @Test
        fun `yields multiple elements when iterable has multiple elements`() {
            val element0 = "A"
            val element1 = "B"
            val element2 = "C"
            val iterator = create(element0, element1, element2).iterator()

            // Multiple calls should return the same result
            assertTrue(iterator.hasNext())
            assertTrue(iterator.hasNext())
            assertTrue(iterator.hasNext())

            // We cannot assume anything about the order of the elements
            val actualElements = setOf(iterator.next(), iterator.next(), iterator.next())
            assertEquals(setOf(element0, element1, element2), actualElements)

            // Multiple calls should return the same result
            assertFalse(iterator.hasNext())
            assertFalse(iterator.hasNext())
            assertFalse(iterator.hasNext())

            assertThrows<NoSuchElementException> { iterator.next() }
        }

        @Test
        fun `yields list of elements when iterable is list and has multiple elements`() {
            val element0 = "A"
            val element1 = "B"
            val element2 = "C"
            val iterable = create(element0, element1, element2)
            val iterator = iterable.iterator()

            // Only when the iterable is ordered
            assumeTrue(iterable is List<*> || iterable is ListView<*>, "Iterable of type ${iterable::class.java.simpleName} is not ordered.")

            // Multiple calls should return the same result
            assertTrue(iterator.hasNext())
            assertTrue(iterator.hasNext())
            assertTrue(iterator.hasNext())

            val actualElements = listOf(iterator.next(), iterator.next(), iterator.next())
            assertEquals(listOf(element0, element1, element2), actualElements)

            // Multiple calls should return the same result
            assertFalse(iterator.hasNext())
            assertFalse(iterator.hasNext())
            assertFalse(iterator.hasNext())

            assertThrows<NoSuchElementException> { iterator.next() }
        }

    }


}
