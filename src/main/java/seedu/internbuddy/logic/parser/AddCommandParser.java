package seedu.internbuddy.logic.parser;

import static seedu.internbuddy.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internbuddy.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.internbuddy.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.internbuddy.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.internbuddy.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.internbuddy.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internbuddy.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.internbuddy.logic.commands.AddCommand;
import seedu.internbuddy.logic.parser.exceptions.ParseException;
import seedu.internbuddy.model.company.Address;
import seedu.internbuddy.model.company.Company;
import seedu.internbuddy.model.company.Email;
import seedu.internbuddy.model.company.Name;
import seedu.internbuddy.model.company.Phone;
import seedu.internbuddy.model.company.Status;
import seedu.internbuddy.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_STATUS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElseGet(() -> "000"));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElseGet(() -> "No Address"));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());

        Company company = new Company(name, phone, email, address, tagList, status);

        return new AddCommand(company);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
