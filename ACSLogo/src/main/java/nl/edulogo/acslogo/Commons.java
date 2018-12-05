package nl.edulogo.acslogo;

import java.util.regex.Pattern;

public class Commons {
    private Commons() {
    }

    public static final Pattern REGEX_INPUT = Pattern.compile("^[A-z][^ \\n\\[\\]()\\-+*/<>=\\\\\"']*$");
}
