package br.ce.wcaquino.tasks.apirest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITest {

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }

    @Test
    public void deveRetornarTarefas(){
        RestAssured.given()
                .when()
                    .get("/todo")
                .then()
                   .statusCode(200)
        ;
    }

    @Test
    public void deveAdicionarTarefaComSucesso(){
        RestAssured.given()
                .body("{ \"task\": \"Teste\", \"dueDate\": \"2021-12-30\" }")
                .contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(201)
        ;

    }
    @Test
    public void NaoDeveAdicionarTarefaInvalida(){
                RestAssured.given()
                    .body("{ \"task\": \"Teste\", \"dueDate\": \"2001-12-30\" }")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .statusCode(400)
        ;

    }



}

