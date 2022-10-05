package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetBookingIdsTest {

    @Test
    public void getBookingIdsWithoutFilterTest() {
        //Obtener una respuesta con los todos los bookingIds de la URI
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        //Imprimimos la respuesta
        response.print();

        //Verificamos que devuelva una respuesta 200
        //Pasamos por parámetro el statusCode que devolvemos de la respuesta, el statusCode que esperamos y un mensaje en caso de que falle
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code should be 200");

        //Creamos una lista con todos los elementos de la respuesta en estilo Json, obteniendo los bookingIds
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        //AssertFalse verifica que la afirmación es falsa
        //Verificamos que la lista no esté vacía y un mensaje en caso de que falle
        Assert.assertFalse(bookingIds.isEmpty(), "List of bookingIds is empty");
    }
}
