package ru.netology.conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.netology.conditional.model.DevProfile;
import ru.netology.conditional.model.ProductionProfile;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    DevProfile devProfile = new DevProfile();
    ProductionProfile productionProfile = new ProductionProfile();

    @Container
    public static GenericContainer<?> myapp1 = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);

    @Container
    public static GenericContainer<?> myapp2 = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @Test
    void contextLoads() {
        ResponseEntity<String> response1 = restTemplate.getForEntity("http://localhost:" +
                myapp1.getMappedPort(8080) +
                "/profile", String.class);
        Assertions.assertEquals(response1.getBody(), devProfile.getProfile());

        ResponseEntity<String> response2 = restTemplate.getForEntity("http://localhost:" +
                myapp2.getMappedPort(8081) +
                "/profile", String.class);
        Assertions.assertEquals(response2.getBody(), productionProfile.getProfile());
    }

}
