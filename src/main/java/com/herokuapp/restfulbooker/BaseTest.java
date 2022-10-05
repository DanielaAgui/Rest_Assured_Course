package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class BaseTest {

    //Clase base donde creamos un booking nuevo
    protected static Response responseCreateBooking() {
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
        Response response = RestAssured.given().contentType(ContentType.JSON).
                //Y un body con el objeto body creado, hacemos un POST a la URI dada
                        body(body.toString()).post("https://restful-booker.herokuapp.com/booking");
        //Retornamos la respuesta
        return response;
    }
}
