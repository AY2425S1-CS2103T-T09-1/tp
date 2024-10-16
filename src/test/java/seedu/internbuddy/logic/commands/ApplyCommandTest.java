package seedu.internbuddy.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.internbuddy.testutil.TypicalApplications.SWE_APPLICATION;
import static seedu.internbuddy.testutil.TypicalCompanies.getTypicalAddressBook;
import static seedu.internbuddy.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.internbuddy.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.internbuddy.commons.core.index.Index;
import seedu.internbuddy.logic.Messages;
import seedu.internbuddy.logic.commands.exceptions.CommandException;
import seedu.internbuddy.model.Model;
import seedu.internbuddy.model.ModelManager;
import seedu.internbuddy.model.UserPrefs;
import seedu.internbuddy.model.application.Application;
import seedu.internbuddy.testutil.ApplicationBuilder;

public class ApplyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Application validApplication = new ApplicationBuilder().build();
        ApplyCommand applyCommand = new ApplyCommand(Index.fromOneBased(999), validApplication);
        assertThrows(CommandException.class, () -> applyCommand.execute(model),
            Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        Application validApplication = SWE_APPLICATION;
        ApplyCommand applyCommand = new ApplyCommand(INDEX_FIRST_COMPANY, validApplication);

        // same object -> returns true
        assertEquals(applyCommand, applyCommand);
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        Application validApplication = SWE_APPLICATION;
        ApplyCommand applyCommand1 = new ApplyCommand(INDEX_FIRST_COMPANY, validApplication);
        ApplyCommand applyCommand2 = new ApplyCommand(INDEX_SECOND_COMPANY, validApplication);

        // different objects -> returns false
        assertEquals(false, applyCommand1.equals(applyCommand2));
    }
}
