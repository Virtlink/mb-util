# Metaborg Utils

## Collections

Here is a proposal for a `ListView` interface implementation:

[mb.util.collections](src/main/java/mb/util/collections)

High-level design goals:
- Performant: where possible, allocations are reduced or removed (e.g., `Iterator` allocations), and where possible, the code will exit early (e.g., a size test before comparing lists)
- It should be easy to implement `ListView` or `CollectionView`; therefore there are many useful default implementations in the interface
- Code should not have to access the actual implementations: all classes are package local, only `ListView<E>` and `CollectionView<E>` are externally visible

Details:
- The methods `of()`, `from()` and `fromCopy()` create new instances from an array of elements, a list, or a copy of a list respectively. The distinction between from() and of() is allow the creation of a list of lists.
- The methods `of()`, `from()` and `fromCopy()` are statically defined on the interfaces `ListView` and `CollectionView`. This way, you can explicitly specify whether you want a `ListView` (using `ListView.of()`) or a `CollectionView` (using `CollectionView.of()`). If `ListView` and `CollectionView` where classes, then `ListView.of()` could not override `CollectionView.of()` and return a different type.
- Depending on the overload called, the `ListView` or `CollectionView` creates a different implementation.
- There is one implementation for empty lists, whose methods can quickly return that the list is empty. There is one instance of the empty list, which is returned for all empty lists.
- There is one implementation for singleton lists, whose methods are optimized for this use case.
- There is one implementation wrapping a list (and one wrapping a collection), delegating all operations to the underlying list or collection.
- There is one implementation wrapping an array. Without this, a `ListView` would wrap a list that wraps an array, and all delegating methods would have another layer of indirection.
- Lists return the same hashcode as the `AbstractList` of Java.
- Lists can be compared to any `Iterable`.
- The default implementations return themselves on a call to `asUnmodifiable()`, making this very cheap.
