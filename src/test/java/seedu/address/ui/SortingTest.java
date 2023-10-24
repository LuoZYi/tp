package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Mod;
import seedu.address.model.tag.Tag;


public class SortingTest {
    @Test
    public void sortTags_correct() {
        // Create a list of Tag objects
        List<Tag> tags = Arrays.asList(
                new Tag("Zebra"),
                new Tag("Bear"),
                new Tag("Bee")
        );

        // Sort the list based on the 'name' attribute
        tags.sort(Comparator.comparing(tag -> tag.name));

        // Define the expected order
        List<Tag> expectedOrder = Arrays.asList(
                new Tag("Bear"),
                new Tag("Bee"),
                new Tag("Zebra")
        );

        // Assert that the list is sorted as expected
        assertEquals(expectedOrder, tags);
    }
    @Test
    public void sortMods_correct() {
        // Create a list of Mod objects
        List<Mod> mods = Arrays.asList(
                new Mod("CS2103T"),
                new Mod("CS1101S"),
                new Mod("CS1231")
        );

        // Sort the list based on the 'name' attribute
        mods.sort(Comparator.comparing(mod -> mod.name));

        // Define the expected order
        List<Mod> expectedOrder = Arrays.asList(
                new Mod("CS1101S"),
                new Mod("CS1231"),
                new Mod("CS2103T")
        );

        // Assert that the list is sorted as expected
        assertEquals(expectedOrder, mods);
    }
}
