package cs.ut.ee.ermmemory2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by teras on 24.03.18.
 */

public class MLString implements Serializable {

    public static Language currentLanguage = Language.Estonian;

    public enum Language {
        Estonian,
        English
    }

    public Map<Language, String> strings;

    public MLString() {
        this.strings = new HashMap<>();
    }

    public String getValue(Language language) {
        return strings.get(language);
    }

    public String getCurrentValue() {
        return strings.get(currentLanguage);
    }

    public void setValue(Language language, String text) {
        strings.put(language, text);
    }

    public static Language getCurrentLanguage() {
        return currentLanguage;
    }

    public static void setCurrentLanguage(Language currentLanguage) {
        MLString.currentLanguage = currentLanguage;
    }

    @Override
    public String toString() {
        return "{Estonian: " + strings.get(Language.Estonian) +
                ", English: " + strings.get(Language.English) + "}";
    }
}
