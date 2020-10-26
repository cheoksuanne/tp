package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_CATEGORY;

import seedu.address.commons.core.category.Category;
import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;

/**
 * Clears all entries in the specified revenue/expense list of the account.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all entries in the specified entry (expense/revenue) list.\n"
            + "Example: " + COMMAND_WORD + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CATEGORY + "expense" + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CATEGORY + "revenue";

    public static final String MESSAGE_CLEAR_ENTRY_SUCCESS = "Cleared all %1$s" + "s";

    public static final String MESSAGE_CLEAR_BOTH_CATEGORY_SUCCESS = "Cleared all expenses and revenues";

    private static final Category NO_CATEGORY = null;

    private final Category category;

    /**
     * Creates an ClearCommand to clear expenses or revenues based on {@code Category}.
     */
    public ClearCommand(Category category) {
        this.category = category;
    }

    /**
     * Creates an ClearCommand to clear all expenses and revenues.
     */
    public ClearCommand() {
        this.category = null;
    }

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        requireAllNonNull(model, activeAccount);
        boolean hasNoCategory = category == NO_CATEGORY;
        activeAccount.setPreviousState();

        if (hasNoCategory) {
            activeAccount.clearExpenses();
            activeAccount.clearRevenues();
        } else {
            boolean isExpense = this.category.isExpense();
            boolean isRevenue = this.category.isRevenue();
            if (isExpense) {
                activeAccount.clearExpenses();
            } else {
                assert isRevenue;
                activeAccount.clearRevenues();
            }
        }

        model.setAccount(activeAccount.getAccount());
        if (hasNoCategory) {
            return CommandResultFactory
                    .createCommandResultForEntryListChangingCommand(
                            MESSAGE_CLEAR_BOTH_CATEGORY_SUCCESS);
        } else {
            return CommandResultFactory
                    .createCommandResultForEntryListChangingCommand(
                            String.format(MESSAGE_CLEAR_ENTRY_SUCCESS, category));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClearCommand) // instanceof handles nulls
                && category.equals(((ClearCommand) other).category); // state check
    }

}
