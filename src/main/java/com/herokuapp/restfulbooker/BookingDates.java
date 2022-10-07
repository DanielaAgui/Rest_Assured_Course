package com.herokuapp.restfulbooker;

//Ya que bookingdates es una archivo json con dos claves, tiene una clase separada
public class BookingDates {

    private String checkin;
    private String checkout;

    //Agregamos el constructor
    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    //Agregamos un constructor vacío para deserialización
    public BookingDates() {
    }

    //Agregamos los getters y setters de las variables
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    //Generamos un método toString()
    @Override
    public String toString() {
        return "BookingDates{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}

