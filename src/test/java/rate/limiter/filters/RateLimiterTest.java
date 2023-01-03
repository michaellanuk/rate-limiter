package rate.limiter.filters;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RateLimiterTest {
    @Test
    public void testAllow() {
        RateLimiter rateLimiter = new RateLimiter(10, 1, TimeUnit.MINUTES);

        for (int i = 0; i < 10; i++) {
            assertTrue(rateLimiter.allow());
        }

        assertFalse(rateLimiter.allow());
    }
}
