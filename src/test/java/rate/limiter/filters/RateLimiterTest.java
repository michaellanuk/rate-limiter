package rate.limiter.filters;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RateLimiterTest {
    @Test
    public void testRateLimiter() {
        RateLimiter limiter = new RateLimiter(10, 1, TimeUnit.MINUTES);

        for (int i = 0; i < 10; i++) {
            // First 10 requests should be allowed
            assertTrue(limiter.allow("1.2.3.4"));
        }

        // Next request should be blocked
        assertFalse(limiter.allow("1.2.3.4"));

        // Request from other IP should be allowed
        assertTrue(limiter.allow("5.6.7.8"));
    }
}