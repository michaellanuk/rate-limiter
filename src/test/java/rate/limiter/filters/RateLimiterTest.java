package rate.limiter.filters;

import org.junit.Test;
import org.junit.Assert;

import java.util.concurrent.TimeUnit;

public class RateLimiterTest {
    @Test
    public void testAllow() {
        RateLimiter rateLimiter = new RateLimiter(10, 1, TimeUnit.MINUTES);

        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(rateLimiter.allow());
        }

        Assert.assertFalse(rateLimiter.allow());
    }
}
