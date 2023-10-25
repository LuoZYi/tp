package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all teaching assistants whose names contain "
            + "any "
            + "of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX KEYWORD [MORE_KEYWORDS]...\n"
            + "Examples: " + COMMAND_WORD + " " + PREFIX_NAME + "alice bob charlie" + ", "
            + COMMAND_WORD + " " + PREFIX_MOD + "cs1231s" + ", "
            + COMMAND_WORD + " " + PREFIX_FROM + "10:00" + " " + PREFIX_TO + "12:00";

    private final ArrayList<Predicate<Person>> predicateList = new ArrayList<>();

    public FindCommand(ArrayList<Predicate<Person>> predicates) {
        this.predicateList.addAll(predicates);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Person> combinedPredicate = predicateList.stream().reduce(x -> true, Predicate::and);
        model.updateFilteredPersonList(combinedPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicateList.equals(otherFindCommand.predicateList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicateList)
                .toString();
    }
}
