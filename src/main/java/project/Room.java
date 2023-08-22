package project;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.*;

//PORT: 28852
@Component
public class Room{
    protected int total_rows = 9;
    protected int total_columns = 9;
    protected ArrayList<Seat> available_seats;
    //transient modifier for Gson serializers
    protected transient Map<String, purchasedSeat> purchased_seats;

    protected transient Stats stats;

    public Stats getStats(){
        return this.stats;
    }


    public void setAvailable_seats(){
        available_seats = new ArrayList<>();
        purchased_seats = new HashMap<>();
        stats = new Stats();
        Collections.synchronizedList(available_seats);
        Collections.synchronizedMap(purchased_seats);
        for(int i=1; i<=9; i++){
            for(int j=1; j<=9; j++){
                available_seats.add(new Seat(i, j));
            }
        }
    }

    public ArrayList<Seat> getAvailable_seats(){
        return available_seats;
    }

    public void setBoughtSeat(purchasedSeat aSeat){
        Iterator<Seat> iterator = available_seats.iterator();
        while(iterator.hasNext()){
            Seat seat = iterator.next();
            if(seat.row == aSeat.getTicket().row && seat.column == aSeat.getTicket().column){
                iterator.remove();
                purchased_seats.put(aSeat.token, aSeat);
                stats.setCurrent_income(aSeat.ticket.getPrice());
                stats.setNumber_of_available_seats(1);
            }
        }

    }

    public int checkSeat(Seat aSeat){
        for(Seat s:available_seats){
            if(s.row == aSeat.row && s.column == aSeat.column){
                return 1;
            }
        }
        return 0;
    }



    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }


    public purchasedSeat returnedSeat(String Token){
        purchasedSeat returnedSeat = purchased_seats.get(Token);
        purchased_seats.remove(Token);
        available_seats.add(new Seat(returnedSeat.ticket.getRow(), returnedSeat.ticket.getColumn()));
        return returnedSeat;
    }

    //FOR RETURNED
    public int checkReturnedSeat(String Token){
        for(var entry:purchased_seats.entrySet()){
            if(Token.equals(entry.getKey())){
                System.out.println(Token); return 1;
            }
        }
        return 0;
    }

    public returnedSeat findPurchasedSeat(String newTok){
        for(var entry:purchased_seats.entrySet()){
            if(newTok.equals(entry.getKey())){
                stats.refund(entry.getValue().ticket.getPrice());
                returnedSeat aReturnedSeat = new returnedSeat(entry.getValue().ticket);
                available_seats.add(new Seat(entry.getValue().ticket.getRow(),entry.getValue().ticket.getColumn()));
                purchased_seats.remove(entry.getKey(), entry.getValue());
                return aReturnedSeat;
            }
        }
        return null;
    }

}
