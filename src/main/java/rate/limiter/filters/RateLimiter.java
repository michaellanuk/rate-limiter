package rate.limiter.filters;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private final long maxRequests;
    private final long duration;
    private final TimeUnit timeUnit;
    private final ConcurrentHashMap<String, Long> startTimes;
    private final ConcurrentHashMap<String, Long> requests;

    public RateLimiter(long maxRequests, long duration, TimeUnit timeUnit) {
        this.maxRequests = maxRequests;
        this.duration = duration;
        this.timeUnit = timeUnit;
        this.startTimes = new ConcurrentHashMap<>();
        this.requests = new ConcurrentHashMap<>();
    }

    public boolean allow(String ip) {
        long elapsedTime = timeUnit.convert(
                System.nanoTime() - startTimes.getOrDefault(ip, System.nanoTime()),
                TimeUnit.NANOSECONDS
        );
        if (elapsedTime > duration) {
            requests.put(ip, 1L);
            startTimes.put(ip, System.nanoTime());
            return true;
        }

        long count = requests.getOrDefault(ip, 0L) + 1;
        requests.put(ip, count);
        if (count > maxRequests) {
            return false;
        }

        return true;
    }
}
