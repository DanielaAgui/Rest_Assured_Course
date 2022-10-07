package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected RequestSpecification spec;

    //Método de configuración
    @BeforeMethod
    public void setUp() {
        //Especificaciones de solicitudes para evitar la duplicación de código
        //Permite especificar como se verá la solicitud (URI, headers, verbos HTTP, etc)
        //Creamos una especificación de solicitud, establecemos la URI base
         spec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();
    }

    //Clase base donde creamos un booking nuevo
    protected Response createBooking() {
        //Creamos un objeto Json con el body que necesitamos para el POST
        JSONObject body = new JSONObject();
        //Agregamos todas las claves según especificaciones de la API con los valores a añadir
        body.put("firstname", "Andrea");
        body.put("lastname", "Mendoza");
        body.put("totalprice", 500);
        body.put("depositpaid", true);

        //Si tenemos otro Json dentro del body de API, lo creamos como un nuevo objeto Json
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2022-10-05");
        bookingdates.put("checkout", "2022-10-31");
        //Y lo añadimos al body
        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "Vegano");

        //Creamos una respuesta dado un dado un header de tipo Json convertido en String
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).
                //Y un body con el objeto body creado, hacemos un POST a la URI dada
                        body(body.toString()).post("/booking");
        //Retornamos la respuesta
        return response;
    }
}
