package com.example.springrestbackendexample;

import com.example.springrestbackendexample.domain.WriterInfo;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.with;

public class LibraryControllerTests {

    RequestSpecification spec = with()
            .baseUri("http://localhost:8080/")
            .basePath("/");

    @Test
    public void bankControllerTest1() {
        WriterInfo[] writerInfos = spec
                .when()
                .get("users/getAll")
            .then()
                .statusCode(200)
                .extract()
                .response()
                .as(WriterInfo[].class);

    }
}
