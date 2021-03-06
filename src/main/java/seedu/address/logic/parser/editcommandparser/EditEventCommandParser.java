package seedu.address.logic.parser.editcommandparser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.editcommand.EditEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Description;

/**
 * Parses input arguments and create a new EditEventCommand object.
 */
public class EditEventCommandParser extends EditCommandParser implements Parser<EditEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditEventCommand and
     * returns an EditEventCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public EditEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GENERAL_EVENT, PREFIX_DATE);
        Index index;

        index = ParserUtil.parseGeneralEventIndex(argMultimap.getPreamble());

        int intIndex = index.getOneBased();
        Description eventEdit = null;
        LocalDateTime dateEdit = null;
        if (arePrefixesPresent(argMultimap, PREFIX_GENERAL_EVENT)) {
            eventEdit = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_GENERAL_EVENT)
                .filter(Description::isValidDescription)
                .orElseThrow(() -> new ParseException(Description.MESSAGE_CONSTRAINTS)));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_DATE)) {
            dateEdit = argMultimap.getValue(PREFIX_DATE)
                    .map(ParserUtil::parseEventDate)
                    .orElseThrow(() -> new ParseException(GeneralEvent.DATE_CONSTRAINT));
        }

        return new EditEventCommand(intIndex, eventEdit, dateEdit);
    }
}
