package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class GetBookingTest {

    @Test
    public void getBookingTest() {
        //Verificamos uno de los bookingIds (del test anterior)
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/918");
        response.print();

        //Con Assert, si una afirmación falla no se ejecutan las otras
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code should be 200");

        //Creamos un objeto SoftAssert
        //Con SoftAssert, si una afirmación falla se ejecutan las demás antes de fallar la prueba
        SoftAssert softAssert = new SoftAssert();
        //Creamos una variable tipo String, que devuelva la respuesta estilo Json, obteniendo el String de la clave dada
        String firstName = response.jsonPath().getString("firstname");
        //Verificamos que el valor obtenido en el String sea igual al que esperamos
        softAssert.assertEquals(firstName, "Sally");

        //Verificamos cada clave de la respuesta obtenida (se está verificando la API)
        String lastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(lastName, "Brown");

        int totalprice = response.jsonPath().getInt("totalprice");
        softAssert.assertEquals(totalprice, 111);

        boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
        //AssertTrue verifica que la afirmación es verdadera
        softAssert.assertTrue(depositPaid);

        //Devolvemos en un String el valor de la clave "checkin"
        String checkIn = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(checkIn, "2013-02-23");

        //Devolvemos en un String el valor de la clave "checkin"
        String checkOut = response.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(checkOut, "2014-10-23");

        String additionalNeeds = response.jsonPath().getString("additionalneeds");
        softAssert.assertEquals(additionalNeeds, "Breakfast");

        //Siempre usar assertAll al final para que verifique todas las afirmaciones, sale un error si alguna falla
        softAssert.assertAll();
    }
}
