package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.availability.FreeTime;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AvailableTimePredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TeachingModPredicate;
import seedu.address.model.tag.Mod;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MOD, PREFIX_FROM, PREFIX_TO);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_MOD, PREFIX_FROM, PREFIX_TO);
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameKeywords = argMultimap.getValue(PREFIX_NAME).get().trim().split("\\s+");
            predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        if (argMultimap.getValue(PREFIX_MOD).isPresent()) {
            Set<Mod> modList = ParserUtil.parseMods(argMultimap.getAllValues(PREFIX_MOD));
            predicates.add(new TeachingModPredicate(new ArrayList<>(modList)));
        }
        if (argMultimap.getValue(PREFIX_FROM).isPresent() && argMultimap.getValue(PREFIX_TO).isPresent()) {
            FreeTime freeTime = ParserUtil.parseFreeTime(argMultimap.getValue(PREFIX_FROM).orElse(null),
                    argMultimap.getValue((PREFIX_TO)).orElse(null));
            // since TimeInterval is same for all days for now, we just use the first day
            predicates.add(new AvailableTimePredicate(freeTime.getDay(0)));
        }
        if (predicates.size() == 0) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand(predicates);
    }

}
