package com.herokuapp.restfulbooker;

//Cada archivo json debe estar en una clase para serializar: convertir un objeto en cadena
public class Booking {
    //Creamos una variable de cada clave
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    //Se agrega el json interno dentro del principal como un objeto de la clase
    private BookingDates bookingDates;
    private String additionalneeds;

    //Agregamos el constructor
    public Booking(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingDates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingDates = bookingDates;
        this.additionalneeds = additionalneeds;
    }

    //Agregamos un constructor vacío para deserialización
    public Booking() {
    }

    //Agregamos los getters y setters de las variables
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingDates() {
        return bookingDates;
    }

    public void setBookingDates(BookingDates bookingDates) {
        this.bookingDates = bookingDates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    //Generamos un método toString()
    @Override
    public String toString() {
        return "Booking{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingDates=" + bookingDates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }
}


