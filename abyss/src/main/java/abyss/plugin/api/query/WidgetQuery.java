package abyss.plugin.api.query;

import java.util.regex.Pattern;

public interface WidgetQuery {

    WidgetQuery id(int id);

    WidgetQuery interactHash(int hash);

    WidgetQuery item(int id, int amount);

    WidgetQuery text(Pattern pattern);

}