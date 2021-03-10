package mb.util.collections

import mb.util.EqualityComparator
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest.dynamicTest

/** Tests the [CollectionView] interface. */
@DisplayName("CollectionView")
interface CollectionViewTests {

    /** Tests the [CollectionView.size] property. */
    @DisplayName("size()")
    interface SizeTests {

        fun <E> create(vararg elements: E): CollectionView<E>

        @TestFactory
        fun `returns correct size`() = listOf(
                listOf<String>(/* empty */) to 0,
                listOf("A") to 1,
                listOf("A", "B") to 2,
                listOf("A", "B", "C") to 3
        ).map { (input, expected) -> dynamicTest("returns $expected when collection is $input") {
            val sut = create(input)
            assertEquals(expected, sut.size())
        } }

    }

    /** Tests the [CollectionView.isEmpty] property. */
    @DisplayName("isEmpty()")
    interface IsEmptyTests {

        fun <E> create(elements: List<E>): CollectionView<E>

        @TestFactory
        fun `returns whether the collection is empty`() = listOf(
                listOf<String>(/* empty */) to true,
                listOf("A") to false,
                listOf("A", "B") to false,
                listOf("A", "B", "C") to false
        ).map { (input, expected) -> dynamicTest("returns $expected when collection is $input") {
            val sut = create(input)
            assertEquals(expected, sut.isEmpty)
        } }

    }

    /** Tests the [CollectionView.getComparator] property. */
    @DisplayName("getComparator()")
    interface GetComparatorTests {

        fun <E> create(comparator: EqualityComparator<E>?, elements: List<E>): CollectionView<E>

        @Test
        fun `returns the comparator of the collection`() {
            val comparator = StringLengthEqualityComparator()
            val sut = create(comparator, listOf("A", "B", "C"))

            assertSame(comparator, sut.comparator)
        }

        @Test
        fun `returns some comparator of the collection`() {
            val sut = create(null, listOf("A", "B", "C"))

            assertNotNull(sut.comparator)
        }

    }

    /** Tests the [CollectionView.contains] method. */
    @DisplayName("contains()")
    interface ContainsTests {

        fun <E> create(comparator: EqualityComparator<E>?, elements: List<E>): CollectionView<E>

        private data class ContainsTest<E>(val elements: List<E>, val element: E?)

        @TestFactory
        fun `returns whether the collection contains an element`() = listOf(
                ContainsTest(listOf<String>(/* empty */), "BB") to false,
                ContainsTest(listOf("A"), "BB") to false,
                ContainsTest(listOf("A", "BB"), "BB") to true,
                ContainsTest(listOf("A", "BB"), "bb") to false,
                ContainsTest(listOf("A", "BB"), "XX") to false,
                ContainsTest(listOf("A", "BB", "CCC"), "BB") to true,
                ContainsTest(listOf("A", "BB", "CCC"), "bb") to false,
                ContainsTest(listOf("A", "BB", "CCC"), "XX") to false
        ).map { (input, expected) -> dynamicTest("returns $expected when collection ${input.elements} contains ${input.element}") {
            val sut = create(null, input.elements)

            assertEquals(expected, sut.contains(input.element))
        } }

        @TestFactory
        fun `returns whether the collection contains an element according to the StringLengthEqualityComparator`() = listOf(
                ContainsTest(listOf<String>(/* empty */), "XX") to false,
                ContainsTest(listOf("A"), "XX") to false,
                ContainsTest(listOf("A", "BB"), "BB") to true,
                ContainsTest(listOf("A", "BB"), "bb") to true,
                ContainsTest(listOf("A", "BB"), "XX") to true,
                ContainsTest(listOf("A", "BB", "CCC"), "BB") to true,
                ContainsTest(listOf("A", "BB", "CCC"), "bb") to true,
                ContainsTest(listOf("A", "BB", "CCC"), "XX") to true
        ).map { (input, expected) -> dynamicTest("returns $expected when collection ${input.elements} contains ${input.element} according to StringLengthEqualityComparator") {
            val sut = create(StringLengthEqualityComparator(), input.elements)

            assertEquals(expected, sut.contains(input.element))
        } }

        @TestFactory
        fun `returns whether the collection contains an element according to the StringCaseInsensitiveEqualityComparator`() = listOf(
                ContainsTest(listOf<String>(/* empty */), "bb") to false,
                ContainsTest(listOf("A"), "bb") to false,
                ContainsTest(listOf("A", "BB"), "BB") to true,
                ContainsTest(listOf("A", "BB"), "bb") to true,
                ContainsTest(listOf("A", "BB"), "XX") to false,
                ContainsTest(listOf("A", "BB", "CCC"), "BB") to true,
                ContainsTest(listOf("A", "BB", "CCC"), "bb") to true,
                ContainsTest(listOf("A", "BB", "CCC"), "XX") to false
        ).map { (input, expected) -> dynamicTest("returns $expected when collection ${input.elements} contains ${input.element} according to StringCaseInsensitiveEqualityComparator") {
            val sut = create(StringCaseInsensitiveEqualityComparator(), input.elements)

            assertEquals(expected, sut.contains(input.element))
        } }

    }

}

@DisplayName("CollectionViewInst")
class CollectionViewInstTests : CollectionViewTests {

    @Nested inner class IteratorTests : IterableTests.IteratorTests {
        override fun <E> create(vararg elements: E): Iterable<E> = ListView.of(*elements)
    }

    @Nested inner class SizeTests : CollectionViewTests.SizeTests {
        override fun <E> create(vararg elements: E): CollectionView<E> = ListView.of(*elements)
    }
}