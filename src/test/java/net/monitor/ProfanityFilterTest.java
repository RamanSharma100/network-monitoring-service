package net.monitor;

import net.monitor.Filters.ProfanityFilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProfanityFilterTest {

    @Test
    public void testContains() {
        ProfanityFilter profanityFilter = new ProfanityFilter();
        assertTrue(profanityFilter.contains("This is a sexy girl"));
        assertFalse(profanityFilter.contains("This is a clean message"));
    }

}
