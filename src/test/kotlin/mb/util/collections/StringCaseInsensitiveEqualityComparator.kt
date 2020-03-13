package mb.util.collections

import mb.util.EqualityComparator
import java.util.*
import kotlin.Comparator

/**
 * Compares strings regardless of case.
 */
class StringCaseInsensitiveEqualityComparator : EqualityComparator<String>, Comparator<String> {

    override fun equals(x: String?, y: String?): Boolean {
        return compare(x, y) == 0
    }

    override fun hashCodeOf(obj: String?): Int {
        return obj?.toLowerCase(Locale.ROOT)?.hashCode() ?: 0
    }

    override fun compare(x: String?, y: String?): Int {
        return if (x == y) { 0 } else { x?.compareTo(y!!, true) ?: -1 }
    }
}