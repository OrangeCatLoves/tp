package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.BoughtPropertyCommand;
import seedu.address.logic.commands.SoldPropertyCommand;
import seedu.address.model.person.Price;

import java.util.Optional;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.*;

public class SoldPropertyCommandParserTest {

    private final SoldPropertyCommandParser parser = new SoldPropertyCommandParser();

    @Test
    public void parse_validArgs_returnSoldCommandWithUpdatedPrice_success() {
        String priceString = "2000000";
        Optional<Price> actualPrice = Optional.of(new Price(priceString));

        String input = INDEX_FIRST_PERSON.getOneBased()
                + " "
                + INDEX_FIRST_PROPERTY.getOneBased()
                + " ap/" + priceString;

        SoldPropertyCommand expectedCommand = new SoldPropertyCommand(
                INDEX_FIRST_PERSON,
                INDEX_FIRST_PROPERTY,
                actualPrice);

        assertParseSuccess(parser, input, expectedCommand);
    }

    @Test
    public void parse_validArgs_returnSoldCommandWithUpdatedPriceAndBigGapInArguments_success() {
        String priceString = "2000000";
        Optional<Price> actualPrice = Optional.of(new Price(priceString));

        String input = INDEX_FIRST_PERSON.getOneBased()
                + "                              "
                + INDEX_FIRST_PROPERTY.getOneBased()
                + " ap/" + priceString;

        SoldPropertyCommand expectedCommand = new SoldPropertyCommand(
                INDEX_FIRST_PERSON,
                INDEX_FIRST_PROPERTY,
                actualPrice);

        assertParseSuccess(parser, input, expectedCommand);
    }

    @Test
    public void parse_validArgs_returnSoldCommandWithNoUpdatedPrice_success() {
        Optional<Price> actualPrice = Optional.ofNullable(null);

        String input = INDEX_FIRST_PERSON.getOneBased()
                + " "
                + INDEX_FIRST_PROPERTY.getOneBased();

        SoldPropertyCommand expectedCommand = new SoldPropertyCommand(
                INDEX_FIRST_PERSON,
                INDEX_FIRST_PROPERTY,
                actualPrice);

        assertParseSuccess(parser, input, expectedCommand);
    }

    @Test
    public void parse_missingArgs_returnSoldCommandFailure() {
        String input = INDEX_FIRST_PERSON.getOneBased()
                + " ";

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoldPropertyCommand.MESSAGE_USAGE);

        assertParseFailure(parser, input, expectedMessage);
    }

    @Test
    public void parse_emptyArgs_returnSoldCommandFailure() {
        String input = " ";

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoldPropertyCommand.MESSAGE_USAGE);

        assertParseFailure(parser, input, expectedMessage);
    }

    @Test
    public void parse_tooManyArgs_returnSoldCommandFailure() {
        String input = INDEX_FIRST_PERSON.getOneBased()
                + " "
                + INDEX_FIRST_PROPERTY.getOneBased()
                + " "
                + INDEX_SECOND_PROPERTY.getOneBased();

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoldPropertyCommand.MESSAGE_USAGE);

        assertParseFailure(parser, input, expectedMessage);
    }
}

