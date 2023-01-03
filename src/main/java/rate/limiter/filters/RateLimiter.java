package rate.limiter.filters;

import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private final long maxRequests;
    private final long duration;
    private final TimeUnit timeUnit;
    private long startTime;
    private long requests;

    public RateLimiter(long maxRequests, long duration, TimeUnit timeUnit) {
        this.maxRequests = maxRequests;
        this.duration = duration;
        this.timeUnit = timeUnit;
        this.startTime = System.nanoTime();
        this.requests = 0;
    }

    public boolean allow() {
        long elapsedTime = timeUnit.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
        if (elapsedTime > duration) {
            requests = 1;
            startTime = System.nanoTime();
            return true;
        }

        requests++;
        if (requests > maxRequests) {
            return false;
        }

        return true;
    }
}
