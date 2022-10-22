package abyss.plugin.api;

import abyss.plugin.api.plugin.attributes.Attributes;
import kotlin.test.Asserter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AttributesTest {

    @Test
    public void testAttributes() {
        Attributes attributes = new Attributes();

        attributes.setBoolean("running", true);
        attributes.setInt("count", 77);
        attributes.setString("name", "david");

        Assertions.assertTrue(attributes.getBoolean("running"));
        Assertions.assertEquals(attributes.getInt("count"), 77);
        Assertions.assertEquals(attributes.getString("name"), "david");

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            attributes.flush();
            attributes.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(out.size() > 0);

        Attributes attr = new Attributes();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        try {
            attr.read(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(attr.getBoolean("running"));
        Assertions.assertEquals(attr.getInt("count"), 77);
        Assertions.assertEquals(attr.getString("name"), "david");

        attr.onIntChanged("count", (old, nw) -> {
            Assertions.assertEquals(old, 77);
            Assertions.assertEquals(nw, 90);
        });

        attr.setInt("count", 90);

    }
}