package abyss.plugin.api;

import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;

/**
 * Text utilities.
 */
public final class TextUtils {

    private TextUtils() {
    }

    /**
     * Filters special characters used by the RuneScape client.
     */
    public static byte[] filterSpecialChars(byte[] bin) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for (byte b : bin) {
            if (b == -62) {
                bos.write(' ');
            } else if (b == -96) {
                // nothing
            } else {
                bos.write(b);
            }
        }
        return bos.toByteArray();
    }

    /**
     * Attempts to find a regular expression inside of a pattern.
     *
     * @param regex The regular expression to find.
     * @param pattern The pattern to search within.
     * @return If the regular expression was found.
     */
    public static boolean regexFind(String regex, String pattern) {
        Pattern p = Pattern.compile(regex);
        return p.matcher(pattern).find();
    }
}
