package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HourTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Hour(null));
    }

    @Test
    public void constructor_invalidHour_throwsNullPointerException() {
        Integer invalidHour = null;
        assertThrows(NullPointerException.class, () -> new Hour(invalidHour));
    }

    @Test
    public void isValidHour() {
        // null working hour
        assertThrows(NullPointerException.class, () -> Hour.isValidHour(null));

        // invalid working hour
        assertFalse(Hour.isValidHour(32805)); // working hour too long
        assertFalse(Hour.isValidHour(-4)); // negative working hour

        // valid working hour
        assertTrue(Hour.isValidHour(38));
    }

    @Test
    public void equals() {
        Hour hour = new Hour(8848);

        // same values -> returns true
        assertEquals(hour, new Hour(8848));

        // same object -> returns true
        assertEquals(hour, hour);

        // default hour -> returns false
        assertNotEquals(0, hour);

        // different types -> returns false
        assertNotEquals(5.0f, hour, "0.0");

        // different values -> returns false
        assertNotEquals(hour, new Hour(884));
    }
}
