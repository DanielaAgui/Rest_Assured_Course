package com.herokuapp.restfulbooker;

//Creamos una clase para deserializar: convertir una cadena en un objeto
public class BookingId {
    //Agregamos todas las variables
    private int bookingid;
    private Booking booking;

    //Agreamos el constructor
    public BookingId(int bookingid, Booking booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    //Agreamos un constructor vacío
    public BookingId() {
    }

    //Agregamos los getters y setters de las variables
    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    //Agregamos el método toString()
    @Override
    public String toString() {
        return "BookingId{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
