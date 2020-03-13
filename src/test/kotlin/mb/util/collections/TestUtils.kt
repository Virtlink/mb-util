package mb.util.collections

import org.opentest4j.TestAbortedException

/**
 * Executes an operation, but aborts the test when the operation throws
 * an [UnsupportedOperationException].
 *
 * @param operation the operation to execute
 * @return the return value of the operation, if any
 */
fun <R> ifSupported(operation: () -> R): R {
    try {
        return operation()
    } catch (_: UnsupportedOperationException) {
        throw TestAbortedException("Operation is not supported.")
    }
}

/**
 * Returns an object only if it is non-null; otherwise aborts the test.
 *
 * @param obj the object to test
 * @return the object, which is guaranteed to be non-null
 */
fun <R> ifNotNull(obj: R?): R {
    if (obj == null)
        throw TestAbortedException("Object is not present.")
    return obj
}