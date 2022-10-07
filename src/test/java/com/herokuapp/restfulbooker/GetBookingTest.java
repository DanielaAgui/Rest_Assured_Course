package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTest extends BaseTest {

    @Test
    public void getBookingTest() {
        //Creamos un booking (método de la clase base)
        Response responseCreate = createBooking();
        responseCreate.print();

        //Obtenemos el id del booking creado
        int bookingId = responseCreate.jsonPath().getInt("bookingid");

        //Establecemos parámetros para usar en el test
        //Se usan para identificar recursos
        spec.pathParam("bookingId", bookingId);

        //Verificamos uno de los bookingIds (del test anterior) y añadimos el endpoint a testear
        //Usamos los parámetros establecidos entre corchetes {}
        Response response = RestAssured.given(spec).get("/booking/{bookingId}");
        response.print();

        //Con Assert, si una afirmación falla no se ejecutan las otras
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code should be 200");

        //Creamos un objeto SoftAssert
        //Con SoftAssert, si una afirmación falla se ejecutan las demás antes de fallar la prueba
        SoftAssert softAssert = new SoftAssert();
        //Creamos una variable tipo String, que devuelva la respuesta estilo Json, obteniendo el String de la clave dada
        String firstName = response.jsonPath().getString("firstname");
        //Verificamos que el valor obtenido en el String sea igual al que esperamos
        softAssert.assertEquals(firstName, "Andrea");

        //Verificamos cada clave de la respuesta obtenida (se está verificando la API)
        String lastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(lastName, "Mendoza");

        int totalprice = response.jsonPath().getInt("totalprice");
        softAssert.assertEquals(totalprice, 500);

        boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
        //AssertTrue verifica que la afirmación es verdadera
        softAssert.assertTrue(depositPaid);

        //Devolvemos en un String el valor de la clave "checkin"
        String checkIn = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(checkIn, "2022-10-05");

        //Devolvemos en un String el valor de la clave "checkin"
        String checkOut = response.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(checkOut, "2022-10-31");

        String additionalNeeds = response.jsonPath().getString("additionalneeds");
        softAssert.assertEquals(additionalNeeds, "Vegano");

        //Siempre usar assertAll al final para que verifique todas las afirmaciones, sale un error si alguna falla
        softAssert.assertAll();
    }

    @Test
    public void getBookingXMLTest() {
        Response responseCreate = createBooking();
        responseCreate.print();

        int bookingId = responseCreate.jsonPath().getInt("bookingid");

        spec.pathParam("bookingId", bookingId);

        //Se puede especificar que sea devuelto en un archivo XML
        //Header xml = new Header("Accept", "application/xml");
        //spec.header(xml);

        Response response = RestAssured.given(spec).get("/booking/{bookingId}");
        response.print();

        Assert.assertEquals(response.getStatusCode(), 200, "Status Code should be 200");

        SoftAssert softAssert = new SoftAssert();

        String firstName = response.jsonPath().getString("firstname");
        softAssert.assertEquals(firstName, "Andrea");

        String lastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(lastName, "Mendoza");

        int totalprice = response.jsonPath().getInt("totalprice");
        softAssert.assertEquals(totalprice, 500);

        boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
        softAssert.assertTrue(depositPaid);

        String checkIn = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(checkIn, "2022-10-05");

        String checkOut = response.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(checkOut, "2022-10-31");

        String additionalNeeds = response.jsonPath().getString("additionalneeds");
        softAssert.assertEquals(additionalNeeds, "Vegano");

        softAssert.assertAll();
    }
}
