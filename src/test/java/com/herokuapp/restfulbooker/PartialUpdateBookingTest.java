package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PartialUpdateBookingTest extends BaseTest {

    @Test
    public void partialUpdateBookingTest() {
        //Creamos un booking (método de la clase base)
        Response responseCreate = responseCreateBooking();
        responseCreate.print();

        //Obtenemos el id del booking creado
        int bookingId = responseCreate.jsonPath().getInt("bookingid");

        //Creamos un nuevo objeto Json con los datos a modificar del anterior
        //Con PATCH solo modificamos algunos campos
        JSONObject body = new JSONObject();
        body.put("firstname", "Daniela");

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2022-10-10");

        //Pasamos la respuesta con un header Json, el body anterior y un método PATCH
        //Ingresamos la autorización de ingreso a la API si es necesario
        Response response = RestAssured.given().auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON).
                        body(body.toString()).patch("https://restful-booker.herokuapp.com/booking/" + bookingId);
        response.print();

        //Verificaciones
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code should be 200");

        SoftAssert softAssert = new SoftAssert();

        String firstName = response.jsonPath().getString("firstname");
        softAssert.assertEquals(firstName, "Daniela");

        String lastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(lastName, "Casas");

        int totalprice = response.jsonPath().getInt("totalprice");
        softAssert.assertEquals(totalprice, 500);

        boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
        softAssert.assertTrue(depositPaid);

        String checkIn = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(checkIn, "2022-10-10");

        String checkOut = response.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(checkOut, "2022-10-31");

        String additionalNeeds = response.jsonPath().getString("additionalneeds");
        softAssert.assertEquals(additionalNeeds, "Vegano");

        softAssert.assertAll();
    }
}
