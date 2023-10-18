package seedu.address.storage;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.FreeTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Mod;
import seedu.address.model.tag.Tag;
import seedu.address.model.person.Hour;

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
    private final String from;
    private final String to;

    private final List<JsonAdaptedMod> mods = new ArrayList<>();
    private final String hour;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("telegram") String telegram,
            @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("from") String from,
            @JsonProperty("to") String to,
            @JsonProperty("mods") List<JsonAdaptedMod> mods,
            @JsonProperty("hour") String hour) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.from = from;
        this.to = to;
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
        from = source.getFreeTime().getFrom();
        to = source.getFreeTime().getTo();
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

        FreeTime modelFreeTime = FreeTime.EMPTY_FREE_TIME;
        if (from != null && to != null) {
            LocalTime start = LocalTime.parse(from);
            LocalTime end = LocalTime.parse(to);
            if (!FreeTime.isValidFreeTime(start, end)) {
                throw new IllegalValueException(FreeTime.MESSAGE_CONSTRAINTS);
            } else {
                modelFreeTime = new FreeTime(start, end);
            }
        }

        final Set<Mod> modelMods = new HashSet<>(personMods);

        if (hour == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Hour.class.getSimpleName()));
        }
        if (!Hour.isValidHour(hour)) {
            throw new IllegalValueException(Hour.MESSAGE_CONSTRAINTS);
        }
        final Hour modelHour = new Hour(hour);
        return new Person(modelName, modelPhone, modelEmail, modelTelegram, modelTags, modelFreeTime, modelMods, modelHour);
    }

}
