package com.github.blaszczyk.scopeviso.postcodeservice;

import com.github.blaszczyk.scopeviso.postcodeservice.domain.Location;
import com.github.blaszczyk.scopeviso.postcodeservice.util.DatabaseContainer;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.ResponseBodySnippet;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Container;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest(classes = { PostCodeServiceApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostcodeServiceApplicationTests {

	@Container
	private static final DatabaseContainer database = new DatabaseContainer();

	@DynamicPropertySource
	static void databaseProperties (DynamicPropertyRegistry registry) {
		database.start();
		registry.add("database.host", database::getHost);
		registry.add("database.port", database::getFirstMappedPort);
		registry.add("database.username", database::getUsername);
		registry.add("database.password", database::getPassword);
		registry.add("database.name", database::getDatabaseName);
		registry.add("spring.liquibase.change-log", () -> "classpath:db/changelog/db.changelog-test.xml");
		assertTrue(database.isRunning());
	}

	@LocalServerPort
	private int port;

	@Test
	void contextLoads() {
	}

	private RequestSpecification spec;

	@BeforeEach
	void setDocumentationConfiguration(RestDocumentationContextProvider restDocumentation) {
		this.spec = new RequestSpecBuilder()
				.setBaseUri("http://localhost:" + port)
				.setBasePath("/postcode/api")
				.setAccept(ContentType.JSON)
				.addFilter(documentationConfiguration(restDocumentation))
				.build();
	}

	@Test
	void get_location_returns_200_with_list_of_locations() {

		final var documentation = document("get_location",
				preprocessResponse(prettyPrint()),
				pathParameters(parameterWithName("postleizzahl").description("Postleitzahl")),
				new ResponseBodySnippet(),
				responseFields(LOCATION_FIELDS)
		);

		final List<Location> responseBody =
			given(this.spec)
				.filter(documentation)
			.when()
				.get("/location/{postleizzahl}", "53227")
			.then()
				.statusCode(200)
				.extract()
				.jsonPath()
				.getList(".", Location.class);
		assertEquals(6, responseBody.size());
		responseBody.forEach(location -> {
			assertEquals("53227", location.postleitzahl());
			assertEquals("Bonn", location.stadt());
			assertEquals("Nordrhein-Westfalen", location.bundesland());
			assertEquals("Bonn", location.kreis());
		});
		final Set<String> bezirke = responseBody.stream()
				.map(Location::bezirk)
				.collect(Collectors.toSet());
		assertEquals(Set.of("Beuel", "Küdinghoven", "Limperich", "Oberkassel", "Pützchen", "Ramersdorf"), bezirke);
	}

	@Test
	void get_location_returns_400_for_illegal_plz() {
		given(this.spec)
		.when()
			.get("/location/{plz}", "keinePlz")
		.then()
			.statusCode(400);
	}

	private static final List<FieldDescriptor> LOCATION_FIELDS = List.of(
			fieldWithPath("[].bundesland").description("Bundesland"),
			fieldWithPath("[].kreis").description("Kreis"),
			fieldWithPath("[].stadt").description("Stadt/Gemeinde"),
			fieldWithPath("[].postleitzahl").description("Postleitzahl"),
			fieldWithPath("[].bezirk").optional().description("Bezirk, optional")
	);
}
