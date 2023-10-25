package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.availability.FreeTime;
import seedu.address.model.availability.TimeInterval;
import seedu.address.model.person.Email;
import seedu.address.model.person.Hour;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Mod;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String telegram;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedTimeInterval> intervals = new ArrayList<>();

    private final List<JsonAdaptedMod> mods = new ArrayList<>();
    private final String hour;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("telegram") String telegram,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("freeTime") List<JsonAdaptedTimeInterval> intervals,
                             @JsonProperty("mods") List<JsonAdaptedMod> mods,
                             @JsonProperty("hour") String hour) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        if (tags != null) {
            this.tags.addAll(tags);
        }

        if (intervals != null) {
            this.intervals.addAll(intervals);
        } else {
            for (int i = 0; i < FreeTime.NUM_DAYS; i++) {
                this.intervals.add(null);
            }
        }
        if (mods != null) {
            this.mods.addAll(mods);
        }
        this.hour = hour;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        telegram = source.getTelegram().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        source.getFreeTime().getIntervals().forEach(interval -> {
            if (interval != null) {
                intervals.add(new JsonAdaptedTimeInterval(interval));
            } else {
                intervals.add(null);
            }
        });
        mods.addAll(source.getMods().stream()
                .map(JsonAdaptedMod::new)
                .collect(Collectors.toList()));
        hour = source.getHour().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        final List<Mod> personMods = new ArrayList<>();
        for (JsonAdaptedMod mod : mods) {
            personMods.add(mod.toModelType());
        }

        int countNulls = 0;
        final List<TimeInterval> personTimeIntervals = new ArrayList<>();
        for (JsonAdaptedTimeInterval timeInterval : intervals) {
            if (timeInterval != null) {
                personTimeIntervals.add(timeInterval.toModelType());
            } else {
                personTimeIntervals.add(null);
                countNulls += 1;
            }
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (telegram == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Telegram.class.getSimpleName()));
        }
        if (!Telegram.isValidTelegram(telegram)) {
            throw new IllegalValueException(Telegram.MESSAGE_CONSTRAINTS);
        }
        final Telegram modelTelegram = new Telegram(telegram);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (!FreeTime.isValidFreeTime(personTimeIntervals)) {
            throw new IllegalValueException(FreeTime.MESSAGE_CONSTRAINTS);
        }
        final FreeTime modelFreeTime = countNulls == FreeTime.NUM_DAYS
                ? FreeTime.EMPTY_FREE_TIME : new FreeTime(personTimeIntervals);

        final Set<Mod> modelMods = new HashSet<>(personMods);

        if (hour == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Hour.class.getSimpleName()));
        }
        if (!Hour.isValidHour(hour)) {
            throw new IllegalValueException(Hour.MESSAGE_CONSTRAINTS);
        }
        final Hour modelHour = new Hour(hour);
        return new Person(modelName, modelPhone, modelEmail, modelTelegram, modelTags,
                modelFreeTime, modelMods, modelHour);
    }

}
