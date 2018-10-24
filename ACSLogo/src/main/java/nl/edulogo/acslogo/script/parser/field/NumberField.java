package nl.edulogo.acslogo.script.parser.field;

import nl.edulogo.acslogo.script.parser.ParsingException;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by Under_Koen on 24/10/2018.
 */
public class NumberField extends Field<Number> {
    public NumberField(String field) {
        super(field, FieldType.NUMBER);
    }

    @Override
    public Number parse() throws ParsingException {
        String number = getField();
        number = number.replace('.', ',');
        try {
            return NumberFormat.getInstance().parse(number);
        } catch (ParseException e) {
            throw new ParsingException(String.format("(%s) should be a number", number));
        }
    }
}