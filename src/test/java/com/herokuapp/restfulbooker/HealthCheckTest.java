package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class HealthCheckTest extends BaseTest {

    @Test
    public void healthCheckTest() {
        //Dado que (se añaden los parámetros de consulta)
        given().
                //Se especifican los parámetros pasados en las especificaciones de solicitudes
                spec(spec);
                //Cuando
        when().
                //Consultamos el endpoint
                get("/ping").
                //Entonces
                then().
                //Verifica que el código de estado sea igual a
                assertThat().statusCode(201);
    }

    @Test
    public void headersAndCookiesTest() {
        //Añadimos un nuevo header
        Header newHeader = new Header("new_header_name", "new_header_value");
        //Establecemos el nuevo header en las especificaciones de solicitud
        spec.header(newHeader);

        //Añadimos una nueva cookie
        Cookie newCookie = new Cookie.Builder("new_cookie_name", "new_cookie_value").build();
        //Establecemos la nueva cookie en las especificaciones de solicitud
        spec.cookie(newCookie);

        //Obtenemos las especificaciones
        //Se agrega log().all() para registrar todas las especificaciones de la solicitud
        Response response = RestAssured.given(spec).log().all().get("/ping");

        //Obtenemos los headers de la respuesta
        Headers headers = response.getHeaders();
        System.out.println("Headers\n" + headers);

        //Obtenemos un header en particular
        Header serverHeader = headers.get("Server");
        //Imprimimos el nombre y el valor del header
        System.out.println(serverHeader.getName() + ": " + serverHeader.getValue());

        //Obtenemos las cookies de la respuesta
        Cookies cookies = response.getDetailedCookies();
        System.out.println("Cookies\n" + cookies);
    }
}
