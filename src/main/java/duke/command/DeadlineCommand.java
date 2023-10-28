package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String EXAMPLE_USAGE = "Example of usage:\ndeadline return book /by 02/12/2019\n" +
            DATE_FORMAT_MESSAGE;
    public static final Pattern ARGUMENT_FORMAT = Pattern.compile("(?<description>\\w.*)\\s/by\\s(?<byArgument>\\w.*)");

    /**
     * Executes the command to create a new deadline task and adds it to the task list.
     *
     * @param tasks           The TaskList containing the tasks to be managed.
     * @param ui              The user interface for displaying messages to the user.
     * @param storage         The storage object used to store and load tasks.
     * @param keywordArgument The parsed user input containing the keyword and arguments.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage, UserKeywordArgument keywordArgument) throws InvalidArgumentException {
        Deadline deadline = processDetail(keywordArgument);
        deadline.execute();
        tasks.add(deadline);
    }

    /**
     * Processes the details provided in the UserKeywordArgument object for creating a Deadline task.
     * Validates the command arguments, extracts necessary details, and constructs a Deadline object.
     *
     * @param keywordArgument The parsed user input containing the keyword and task details.
     * @return A Deadline object created based on the processed details.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     * message.
     */
    private Deadline processDetail(UserKeywordArgument keywordArgument) throws InvalidArgumentException {
        validateKeywordArgument(keywordArgument, new DeadlineCommand());
        final Matcher byMatcher = ARGUMENT_FORMAT.matcher(keywordArgument.getArguments());
        validateArgumentMatcher(byMatcher, new DeadlineCommand(), "/by");

        final String description = byMatcher.group("description");
        final String byArgument = byMatcher.group("byArgument");
        final Matcher dateMatcher = DATE_ARG_FORMAT.matcher(byArgument);
        validateDateMatcher(dateMatcher, new DeadlineCommand(), "");

        LocalDateTime by = Parser.constructDateTime(dateMatcher);
        return new Deadline(false, description, by);
    }

    /**
     * Gets the example usage string for the command.
     *
     * @return The example usage string demonstrating how to use the command.
     */
    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }

    /**
     * Gets the command word associated with the command.
     *
     * @return The command word representing the keyword for the command.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
