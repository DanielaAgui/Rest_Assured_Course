package com.herokuapp.restfulbooker;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class HealthCheckTest {

    @Test
    public void healthCheckTest() {
        //Dado que (se añaden los parámetros de consulta)
        given().
                //Cuando (métodos HTTP)
                when().
                //Consulta la URL de la página a probar
                get("https://restful-booker.herokuapp.com/apidoc/index.html#api-Ping").
                //Entonces
                then().
                //Verifica que el código de estado sea igual a 200
                assertThat().statusCode(200);
    }
}
