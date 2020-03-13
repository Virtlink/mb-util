package mb.util.collections

import mb.util.EqualityComparator

/**
 * Compares strings by their length.
 */
class StringLengthEqualityComparator : EqualityComparator<String>, Comparator<String> {

    override fun equals(x: String?, y: String?): Boolean {
        return compare(x, y) == 0
    }

    override fun hashCodeOf(obj: String?): Int {
        return obj?.length ?: 0
    }

    override fun compare(x: String?, y: String?): Int {
        return (x?.length ?: -1).compareTo((y?.length ?: -1))
    }
}