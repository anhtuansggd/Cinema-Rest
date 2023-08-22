package project;

import java.util.UUID;

public class purchasedSeat {
    protected String token;
    protected Seat ticket;

    public purchasedSeat(Seat aSeat){
        aSeat.setPrice();
        this.ticket = aSeat;
        this.token = String.valueOf(UUID.randomUUID());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

}
