package rate.limiter.filters;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RateLimiterTest {
    @Test
    public void testAllow() {
        int maxRequests = 10;
        RateLimiter rateLimiter = new RateLimiter(maxRequests, 1, TimeUnit.MINUTES);

        for (int i = 0; i < maxRequests; i++) {
            // First 10 requests should be allowed
            assertTrue(rateLimiter.allow("1.2.3.4"));
        }

        // Next request should be blocked
        assertFalse(rateLimiter.allow("1.2.3.4"));

        // Request from other IP should be allowed
        assertTrue(rateLimiter.allow("5.6.7.8"));
    }
}