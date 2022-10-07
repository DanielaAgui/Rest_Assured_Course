package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetBookingIdsTest extends BaseTest{

    @Test
    public void getBookingIdsWithoutFilterTest() {
        //Obtener una respuesta con los todos los bookingIds de la URI
        Response response = RestAssured.given(spec).get("/booking");
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

    @Test
    public void getBookingIdsWithFilterTest() {
        //Se pueden crear parámetros de consulta para filtrar en las API, se usan en la URI
        //Sintaxis: URI + ?'clave'='valor' (ejm: URI?firstname=Susan)
        //Podemos agregar los filtros desde las especificaciones de solicitudes
        spec.queryParam("firstname", "Susan");

        Response response = RestAssured.given(spec).get("/booking");
        response.print();

        Assert.assertEquals(response.getStatusCode(), 200, "Status Code should be 200");

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");

        Assert.assertFalse(bookingIds.isEmpty(), "List of bookingIds is empty");
    }
}
