package rate.limiter.filters;

import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import jakarta.inject.Singleton;
import rate.limiter.helpers.AddressResolver;

import static io.micronaut.http.annotation.Filter.MATCH_ALL_PATTERN;

@Singleton
@Filter(MATCH_ALL_PATTERN)
public class RateLimiterFilter implements HttpServerFilter {
    private final RateLimiter rateLimiter = new RateLimiter(10, 1, TimeUnit.MINUTES);

    private final AddressResolver addressResolver = new AddressResolver();


    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        if (!rateLimiter.allow(addressResolver.resolve(request))) {
            return Publishers.just(HttpResponse.status(HttpStatus.TOO_MANY_REQUESTS).body("Too Many Requests"));
        }

        return chain.proceed(request);
    }
}
