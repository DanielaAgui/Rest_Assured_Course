package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateBookingTest extends BaseTest {

    @Test
    public void updateBookingTest() {
        //Creamos un booking (método de la clase base)
        Response responseCreate = createBooking();
        responseCreate.print();

        //Obtenemos el id del booking creado
        int bookingId = responseCreate.jsonPath().getInt("bookingid");

        //Creamos un nuevo objeto Json con los datos a modificar del anterior
        JSONObject body = new JSONObject();
        body.put("firstname", "Mario");
        body.put("lastname", "Casas");
        body.put("totalprice", "125");
        body.put("depositpaid", false);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2022-10-05");
        bookingdates.put("checkout", "2022-10-31");

        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "Vegano");

        //Pasamos la respuesta con un header Json, el body anterior y un método PUT
        //Ingresamos la autorización de ingreso a la API si es necesario
        //Con preemptive() se manda la autenticación aunque el servidor no la haya solicitado
        //Se envía una autorización básica que consta de un usuario y contraseña
        Response response = RestAssured.given(spec).auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON).
                        body(body.toString()).put("/booking/" + bookingId);
        response.print();

        //Verificaciones
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code should be 200");

        SoftAssert softAssert = new SoftAssert();

        String firstName = response.jsonPath().getString("firstname");
        softAssert.assertEquals(firstName, "Mario");

        String lastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(lastName, "Casas");

        int totalprice = response.jsonPath().getInt("totalprice");
        softAssert.assertEquals(totalprice, 125);

        boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
        softAssert.assertFalse(depositPaid);

        String checkIn = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(checkIn, "2022-10-05");

        String checkOut = response.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(checkOut, "2022-10-31");

        String additionalNeeds = response.jsonPath().getString("additionalneeds");
        softAssert.assertEquals(additionalNeeds, "Vegano");

        softAssert.assertAll();
    }
}
