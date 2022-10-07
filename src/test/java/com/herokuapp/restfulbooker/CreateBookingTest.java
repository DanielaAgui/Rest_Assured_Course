package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.print.Book;

public class CreateBookingTest extends BaseTest {

    @Test
    public void createBookingTest() {
        //La respuesta es el método de la base para que los datos sean siempre los mismos
        Response response = createBooking();
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

    //POJO (Plain Old Java Object): clase u objeto sencillo que no depende de un framework
    @Test
    public void createBookingSerializationTest() {
        //Creamos una instancia de clase de las fechas (json interno)
        BookingDates bookingDates = new BookingDates("2022-10-05", "2022-10-31");
        //Creamos una instancia de clase del json principal con sus argumentos, e incluimos la instancia de clase del json interno
        Booking body = new Booking("Camilo", "Zapata", 200, false, bookingDates, "Baby crib");

        //Mandamos el json al endpoint en la respuesta
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).
                        body(body).post("/booking");
        response.print();

        //Verificaciones
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code should be 200");

        SoftAssert softAssert = new SoftAssert();

        String firstName = response.jsonPath().getString("booking.firstname");
        softAssert.assertEquals(firstName, "Camilo");

        String lastName = response.jsonPath().getString("booking.lastname");
        softAssert.assertEquals(lastName, "Zapata");

        int totalprice = response.jsonPath().getInt("booking.totalprice");
        softAssert.assertEquals(totalprice, 200);

        boolean depositPaid = response.jsonPath().getBoolean("booking.depositpaid");
        softAssert.assertFalse(depositPaid);

        String checkIn = response.jsonPath().getString("booking.bookingdates.checkin");
        softAssert.assertEquals(checkIn, "2022-10-05");

        String checkOut = response.jsonPath().getString("booking.bookingdates.checkout");
        softAssert.assertEquals(checkOut, "2022-10-31");

        String additionalNeeds = response.jsonPath().getString("booking.additionalneeds");
        softAssert.assertEquals(additionalNeeds, "Baby crib");

        softAssert.assertAll();
    }

    @Test
    public void createBookingDeserializationTest() {
        //Creamos una instancia de clase de las fechas (json interno)
        BookingDates bookingDates = new BookingDates("2022-10-05", "2022-10-31");
        //Creamos una instancia de clase del json principal con sus argumentos, e incluimos la instancia de clase del json interno
        Booking body = new Booking("Camilo", "Zapata", 200, false, bookingDates, "Baby crib");

        //Mandamos el json al endpoint en la respuesta
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).
                body(body).post("/booking");
        response.print();

        //Convertimos el body de la respuesta en una clase
        BookingId bookingId = response.as(BookingId.class);

        //Verificaciones
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code should be 200");

        //Verificamos que el body obtenido de la respuesta es el mismo que el obtenido de la solicitud
        Assert.assertEquals(bookingId.getBooking().toString(), body.toString());
    }
}
