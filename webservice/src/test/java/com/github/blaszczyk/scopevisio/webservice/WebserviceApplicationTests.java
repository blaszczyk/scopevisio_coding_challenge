package com.github.blaszczyk.scopevisio.webservice;

import com.github.blaszczyk.scopevisio.webservice.util.MockService;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = { WebserviceApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebserviceApplicationTests {

	private static final MockService mockPostcodeService = new MockService();

	private static final MockService mockPraemienService = new MockService();

	@DynamicPropertySource
	static void postcodeServiceProperties (DynamicPropertyRegistry registry) {
		registry.add("postcode-service.port", mockPostcodeService::port);
		registry.add("praemien-service.port", mockPraemienService::port);
	}

	@LocalServerPort
	private int port;

	@BeforeAll
	static void startMockServices() {
		mockPostcodeService.start();
		mockPraemienService.start();
	}

	@BeforeEach
	void clearExpectations() {
		mockPostcodeService.resetAll();
		mockPraemienService.resetAll();
	}

	@AfterEach
	void checkUnmatchedRequests() {
		mockPostcodeService.checkForUnmatchedRequests();
		mockPraemienService.checkForUnmatchedRequests();
	}

	@AfterAll
	static void stopMockServices() {
		mockPostcodeService.stop();
		mockPraemienService.stop();
	}

	private RequestSpecification spec;

	@BeforeEach
	void setDocumentationConfiguration() {
		this.spec = new RequestSpecBuilder()
				.setBaseUri("http://localhost:" + port)
				.build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void routes_requests_to_postcode_service() {

		final String testPath = "/postcode/api/anything/could/be/here";
		final byte[] expectedResponse = "test-response".getBytes(StandardCharsets.UTF_8);
		final int statusCode = 200;

		mockPostcodeService.createGetExpectation(testPath, statusCode, expectedResponse);

		final byte[] actualResponse = given(this.spec)
				.when()
				.get(testPath)
				.then()
				.statusCode(statusCode)
				.extract()
				.body()
				.asByteArray();
		assertArrayEquals(expectedResponse, actualResponse);
	}

	@Test
	void routes_requests_to_praemien_service() {

		final String testPath = "/praemien/api/random/skjasnkfbkjasd";
		final byte[] expectedResponse = "ÄÖÜ+ß?+,.#!§$&%/".getBytes(StandardCharsets.UTF_8);
		final int statusCode = 404;

		mockPraemienService.createGetExpectation(testPath, statusCode, expectedResponse);

		final byte[] actualResponse = given(this.spec)
				.when()
				.get(testPath)
				.then()
				.statusCode(statusCode)
				.extract()
				.body()
				.asByteArray();
		assertArrayEquals(expectedResponse, actualResponse);
	}

}
