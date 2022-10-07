package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTest extends BaseTest {

    @Test
    public void deleteBookingTest() {
        //Creamos un booking (método de la clase base)
        Response responseCreate = createBooking();
        responseCreate.print();

        //Obtenemos el id del booking creado
        int bookingId = responseCreate.jsonPath().getInt("bookingid");

        //Pasamos la respuesta con un header Json y un método DELETE
        //Ingresamos la autorización de ingreso a la API si es necesario
        Response response = RestAssured.given(spec).auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON).
                        delete("/booking/" + bookingId);
        response.print();

        //Verificaciones
        Assert.assertEquals(response.getStatusCode(), 201, "Status Code should be 201");

        //Verificamos que el Json se eliminó correctamente
        Response responseGet = RestAssured.get("https://restful-booker.herokuapp.com/booking/\" + bookingId");
        responseGet.print();
    }
}
