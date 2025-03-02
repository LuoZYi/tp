package seedu.address.testutil;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.availability.FreeTime;
import seedu.address.model.person.Email;
import seedu.address.model.person.Hour;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Mod;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEGRAM = "@amybee";
    public static final String DEFAULT_HOUR = "8";
    private Name name;
    private Phone phone;
    private Email email;
    private Telegram telegram;
    private Set<Tag> tags;
    private FreeTime freeTime;
    private Set<Mod> mods;
    private Hour hour;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        tags = new HashSet<>();
        freeTime = FreeTime.EMPTY_FREE_TIME;
        mods = new HashSet<>();
        hour = new Hour(DEFAULT_HOUR);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        telegram = personToCopy.getTelegram();
        tags = new HashSet<>(personToCopy.getTags());
        freeTime = personToCopy.getFreeTime();
        mods = new HashSet<>(personToCopy.getMods());
        hour = personToCopy.getHour();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code mods} into a {@code Set<Mod>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withMods(String... mods) {
        this.mods = SampleDataUtil.getModSet(mods);
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegram(String telegram) {
        this.telegram = new Telegram(telegram);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code FreeTime} of the {@code Person} that we are building.
     */
    public PersonBuilder withFreeTime(String from, String to) {
        if (from == null || to == null) {
            this.freeTime = FreeTime.EMPTY_FREE_TIME;
        } else {
            this.freeTime = new FreeTime(LocalTime.parse(from), LocalTime.parse(to));
        }
        return this;
    }

    /**
     * Sets the {@code Hour} of the {@code Person} that we are building.
     */
    public PersonBuilder withHour(String hour) {
        this.hour = new Hour(hour);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, telegram, tags, freeTime, mods, hour);
    }

}
