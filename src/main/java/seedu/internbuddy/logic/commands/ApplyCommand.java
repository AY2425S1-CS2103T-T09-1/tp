package seedu.internbuddy.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internbuddy.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.internbuddy.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.internbuddy.commons.core.index.Index;
import seedu.internbuddy.commons.util.ToStringBuilder;
import seedu.internbuddy.logic.Messages;
import seedu.internbuddy.logic.commands.exceptions.CommandException;
import seedu.internbuddy.model.Model;
import seedu.internbuddy.model.application.Application;
import seedu.internbuddy.model.company.Company;

/**
 * Adds an application to a company in the address book.
 */
public class ApplyCommand extends Command {

    public static final String COMMAND_WORD = "apply";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an application to the company. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Full Stack Engineer "
            + PREFIX_DESCRIPTION + "Requires knowledge in ReactJS and ExpressJS";

    public static final String MESSAGE_SUCCESS = "New application added: %1$s";

    private final Application toAdd;
    private final Index index;

    /**
     * Creates an ApplyCommand to add the specified {@code Application}
     */
    public ApplyCommand(Index index, Application application) {
        requireNonNull(index);
        requireNonNull(application);
        this.index = index;
        toAdd = application;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToEdit = lastShownList.get(index.getZeroBased());
        companyToEdit.addApplication(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplyCommand)) {
            return false;
        }

        ApplyCommand otherApplyCommand = (ApplyCommand) other;
        return toAdd.equals(otherApplyCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
