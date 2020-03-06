package mb.util.collections;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTests {

    // NOTE: These tests succeed because the default implementation of ListView can be cast to List.
    // By default, ListView.equals(List) will work but List.equals(ListView) will not.

    @Test
    public void createListFromElements() {
        ListView<String> list = ListView.of("a", "b", "c");

        assertEquals(Arrays.asList("a", "b", "c"), list);
    }

    @Test
    public void createSingletonListFromElement() {
        ListView<String> list = ListView.of("a");

        assertEquals(Collections.singletonList("a"), list);
    }

    @Test
    public void createEmptyList() {
        ListView<String> list = ListView.of();

        assertEquals(Collections.emptyList(), list);
    }

    @Test
    public void createListOfList() {
        ListView<ListView<String>> list = ListView.of(ListView.of("a", "b", "c"));

        assertEquals(Collections.singletonList(Arrays.asList("a", "b", "c")), list);
    }

    @Test
    public void createListFromList() {
        ListView<String> list = ListView.from(Arrays.asList("a", "b", "c"));

        assertEquals(Arrays.asList("a", "b", "c"), list);
    }

}
