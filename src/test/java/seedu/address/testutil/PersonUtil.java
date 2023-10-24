package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.FreeTime;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Mod;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_TELEGRAM + person.getTelegram().value + " ");

        if (!person.getFreeTime().equals(FreeTime.EMPTY_FREE_TIME)) {
            sb.append(PREFIX_FROM + person.getFreeTime().getFrom() + " ");
            sb.append(PREFIX_TO + person.getFreeTime().getTo() + " ");
        }
        person.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.name + " ")
        );
        person.getMods().stream().forEach(
            s -> sb.append(PREFIX_MOD + s.name + " ")
        );
        sb.append(PREFIX_HOUR + person.getHour().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getTelegram().ifPresent(address -> sb.append(PREFIX_TELEGRAM).append(address.value).append(" "));
        descriptor.getFreeTime().ifPresent(freeTime -> sb.append(PREFIX_FROM).append(freeTime.getFrom()).append(" ")
                .append(PREFIX_TO).append(freeTime.getTo()).append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            tags.forEach(s -> sb.append(PREFIX_TAG).append(s.name).append(" "));
        }
        if (descriptor.getMods().isPresent()) {
            Set<Mod> mods = descriptor.getMods().get();
            mods.forEach(s -> sb.append(PREFIX_MOD).append(s.name).append(" "));
        }
        descriptor.getHour().ifPresent(hour -> sb.append(PREFIX_HOUR).append(hour.value).append(" "));
        return sb.toString();
    }
}
