package by.restaurant.util;

/**
 * Created by Pavel on 11.06.2017.
 */
public class StringParser {

    public static String insertPeriodically(
            String text, String insert, int period)
    {
        StringBuilder builder = new StringBuilder(
                text.length() + insert.length() * (text.length()/period)+1);
        int index = 0;
        String prefix = "";
        while (index < text.length())
        {
            builder.append(prefix);
            prefix = insert;
            builder.append(text.substring(index,
                    Math.min(index + period, text.length())));
            index += period;
        }
        return builder.toString();
    }
}
