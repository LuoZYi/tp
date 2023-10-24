package seedu.address.model.tag;

import org.junit.jupiter.api.Test;

public class LabelTest {
    @Test
    public void matchName_true() {
        Label label = Tag.of("label");
        assert label.matchName("label");
    }

    @Test
    public void matchName_false() {
        Label label = Tag.of("label");
        assert !label.matchName("label2");
    }
}
