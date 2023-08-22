package project.controller;

import project.*;
import project.Exception.UnauthorizedException;
import project.Exception.generalErrorMessageException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    protected Room aRoom;

    @PostConstruct
    public void initialzeRoom(){
        aRoom.setAvailable_seats();
    }

    @GetMapping(value = "/seats")
    public String getaRoom() throws JsonProcessingException {
        Gson gson = new Gson();
        return gson.toJson(aRoom);
    }

    @PostMapping(value = "/purchase")
    public String purchaseSeat (@RequestBody Seat aSeat) {
        if((aSeat.getRow() <1 || aSeat.getColumn() <1 ) || ( aSeat.getRow() > 9 || aSeat.getColumn() > 9)){
            throw new generalErrorMessageException("The number of a row or a column is out of bounds!");
        }else if(aRoom.checkSeat(aSeat)==0){
            throw new generalErrorMessageException("The ticket has been already purchased!");
        }else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            purchasedSeat boughtSeat = new purchasedSeat(aSeat);
            aRoom.setBoughtSeat(boughtSeat);
            return gson.toJson(boughtSeat);
        }
    }

    @PostMapping(value = "/return")
    public String returnSeat(@RequestBody String Token){
        //deserialize json java gson string
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = gson.fromJson(Token, JsonObject.class);
        String newTok = jsonObject.get("token").getAsString();
        if(aRoom.checkReturnedSeat(newTok)==0){
            throw new generalErrorMessageException("Wrong token!");
        }else{
            returnedSeat aReturnedSeat = aRoom.findPurchasedSeat(newTok);
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(aReturnedSeat));
            return gson.toJson(aReturnedSeat);
        }
    }

    @GetMapping(value = "/stats")
    public String getStats(@RequestParam(required = false) String password){
        if(password==null || password.isEmpty()){
            throw new UnauthorizedException("The password is wrong!");
        }else if(password.equals("super_secret")){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(aRoom.getStats());
        }else
        throw new UnauthorizedException("The password is wrong!");
    }
}

