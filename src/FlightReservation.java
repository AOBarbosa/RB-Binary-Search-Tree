public class FlightReservation {
    private int code;
    private String passengerName;
    private String flight;
    private String departureHour;

    public FlightReservation() {
        this.code = 0;
        this.passengerName = "";
        this.flight = "";
        this.departureHour = "";
    }

    public FlightReservation(int code, String passengerName, String flight, String departureHour) {
        this.code = code;
        this.passengerName = passengerName;
        this.flight = flight;
        this.departureHour = departureHour;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getDepartureHour() {
        return departureHour;
    }

    public void setDepartureHour(String departureHour) {
        this.departureHour = departureHour;
    }
}
