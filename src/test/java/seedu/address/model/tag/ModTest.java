package seedu.address.model.tag;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModTest {
    @Test
    public void equalsToItself_true() {
        Mod mod = Mod.of("CS2103T");
        assert mod.equals(mod);
    }

    @Test
    public void equalsToOtherModWithSameName_true() {
        Mod mod1 = Mod.of("CS2103T");
        Mod mod2 = Mod.of("CS2103T");
        assert mod1.equals(mod2);
    }

    @Test
    public void equalsToOtherModWithDifferentName_false() {
        Mod mod1 = Mod.of("CS2103T");
        Mod mod2 = Mod.of("CS2101");
        assert !mod1.equals(mod2);
    }

    @Test
    public void equalsToOtherTag_false() {
        Mod mod = Mod.of("CS2103T");
        Tag tag = Tag.of("CS2103T");
        assert !mod.equals(tag);
    }

    @Test
    public void equalsToNull_false() {
        Mod mod = Mod.of("CS2103T");
        assert !mod.equals(null);
    }
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Mod.of(null));
    }

    @Test
    public void constructor_invalidModName_throwsIllegalArgumentException() {
        String invalidModName = "";
        assertThrows(IllegalArgumentException.class, () -> Mod.of(invalidModName));
    }

    @Test
    public void isValidModName() {
        // null mod name
        assertThrows(NullPointerException.class, () -> Mod.isValidModName(null));
    }
}
