package seedu.internbuddy.logic.parser;

import static seedu.internbuddy.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internbuddy.logic.parser.CliSyntax.PREFIX_APPSTATUS;
import static seedu.internbuddy.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.internbuddy.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.internbuddy.commons.core.index.Index;
import seedu.internbuddy.logic.commands.ApplyCommand;
import seedu.internbuddy.logic.parser.exceptions.ParseException;
import seedu.internbuddy.model.application.AppStatus;
import seedu.internbuddy.model.application.Application;
import seedu.internbuddy.model.application.Description;
import seedu.internbuddy.model.name.Name;

/**
 * Parses input arguments and creates a new ApplyCommand object
 */
public class ApplyCommandParser implements Parser<ApplyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ApplyCommand
     * and returns an ApplyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ApplyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_APPSTATUS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ApplyCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DESCRIPTION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ApplyCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DESCRIPTION);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        AppStatus appStatus = ParserUtil.parseAppStatus(argMultimap.getValue(PREFIX_APPSTATUS)
                .orElse("APPLIED"));

        Application application = new Application(name, description, appStatus);

        return new ApplyCommand(index, application);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
