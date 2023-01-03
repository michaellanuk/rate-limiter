package rate.limiter.helpers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.server.util.HttpClientAddressResolver;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class AddressResolver implements HttpClientAddressResolver {
    @Override
    public String resolve(HttpRequest request) {
        String xff = request.getHeaders().get("X-Forwarded-For");
        if (xff != null) {
            return xff.split(",")[0].trim();
        }

        // If the request was not proxied through a trusted proxy, we can
        // get the client's IP address from the remote address.
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        InetAddress inetAddress = remoteAddress.getAddress();
        return inetAddress.getHostAddress();
    }
}
