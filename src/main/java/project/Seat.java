package project;

import org.springframework.stereotype.Component;

@Component
public class Seat {
    protected int row;
    protected int column;

    protected int price;


    public Seat(){}

    public Seat(Seat aSeat){
        this.row = aSeat.row;
        this.column = aSeat.column;
    }

    public Seat(int row, int column){
        this.row = row;
        this.column = column;
        this.price = getPrice();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setPrice(){
        this.price = getPrice();
    }
    public int getPrice(){
        if(this.row <= 4){
            return 10;
        }else return 8;
    }

}
