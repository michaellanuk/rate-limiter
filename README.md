## Rate Limiter

Custom rate-limiter from scratch using a sliding-window algorithm built with Micronaut.

### Prerequisites

- Java 8 or higher
- Maven 3.3.9 or higher

### Building and running the application

Use the following command to build and run the application
```
mvn mn:run
```

By default, the application will run on port 8080. You can then use the following
cURL request to test it out (default configuration is 10 max requests within a minute).

```bash
for i in {1..20}; do curl http://localhost:8080; done
```

### Testing

Run tests with
```
mvn test
```

## Micronaut

### Micronaut 3.8.0 Documentation

- [User Guide](https://docs.micronaut.io/3.8.0/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.8.0/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.8.0/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

### Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)


