package project;

public class Stats {
    protected int current_income=0;
    protected int number_of_available_seats = 81;
    protected int number_of_purchased_tickets = 0;

    public int getCurrent_income() {
        return current_income;
    }

    public void setCurrent_income(int new_income) {
        this.current_income += new_income;
    }

    public void refund(int money) {
        this.current_income -= money;
        this.number_of_available_seats++;
        this.number_of_purchased_tickets--;
    }

    public void refundSeat() {
        this.number_of_available_seats++;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public void setNumber_of_available_seats(int new_purchased_seat) {
        this.number_of_available_seats -= new_purchased_seat;
        this.number_of_purchased_tickets = 81-this.number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }
}
