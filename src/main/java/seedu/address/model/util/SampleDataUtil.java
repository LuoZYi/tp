package seedu.address.model.util;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.*;
import seedu.address.model.tag.Mod;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 * @formatter:off
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Telegram("@alexyeoh"),
                        getTagSet("friends"),
                        new FreeTime(LocalTime.parse("11:30"), LocalTime.parse("12:30")),
                        getModSet("CS2103T"), new Hour("8")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Telegram("@berrrrrnice"),
                        getTagSet("colleagues", "friends"),
                        new FreeTime(LocalTime.parse("12:30"), LocalTime.parse("13:30")),
                        getModSet("CS2103T"), new Hour("2")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Telegram("@heyimcharlotte"),
                        getTagSet("neighbours"),
                        null,
                        getModSet("CS2103T"), new Hour("8")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Telegram("@davidli123"),
                        getTagSet("family"),
                        new FreeTime(LocalTime.parse("14:30"), LocalTime.parse("15:30")),
                        getModSet("CS2103T"), new Hour("10")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Telegram("@irfannn"),
                        getTagSet("classmates"),
                        null,
                        getModSet("CS1231S"), new Hour("20")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Telegram("@rollieroy"),
                        getTagSet("colleagues"),
                        new FreeTime(LocalTime.parse("11:30"), LocalTime.parse("12:30")),
                        getModSet("CS1231S"), new Hour("5"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a mod set containing the list of strings given.
     */
    public static Set<Mod> getModSet(String... strings) {
        return Arrays.stream(strings)
                .map(Mod::new)
                .collect(Collectors.toSet());
    }
}
