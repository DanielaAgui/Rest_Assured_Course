package com.herokuapp.restfulbooker;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateBookingTest extends BaseTest {

    @Test
    public void createBookingTest() {
        //La respuesta es el método de la base para que los datos sean siempre los mismos
        Response response = responseCreateBooking();
        response.print();

        //Verificaciones
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code should be 200");

        SoftAssert softAssert = new SoftAssert();

        String firstName = response.jsonPath().getString("booking.firstname");
        softAssert.assertEquals(firstName, "Andrea");

        String lastName = response.jsonPath().getString("booking.lastname");
        softAssert.assertEquals(lastName, "Mendoza");

        int totalprice = response.jsonPath().getInt("booking.totalprice");
        softAssert.assertEquals(totalprice, 500);

        boolean depositPaid = response.jsonPath().getBoolean("booking.depositpaid");
        softAssert.assertTrue(depositPaid);

        String checkIn = response.jsonPath().getString("booking.bookingdates.checkin");
        softAssert.assertEquals(checkIn, "2022-10-05");

        String checkOut = response.jsonPath().getString("booking.bookingdates.checkout");
        softAssert.assertEquals(checkOut, "2022-10-31");

        String additionalNeeds = response.jsonPath().getString("booking.additionalneeds");
        softAssert.assertEquals(additionalNeeds, "Vegano");

        softAssert.assertAll();
    }
}
