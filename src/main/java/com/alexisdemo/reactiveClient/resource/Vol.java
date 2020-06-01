package com.alexisdemo.reactiveClient.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vol {

    String departure;
    String arrival;
    Double price;
    LocalDateTime departureDateTime;
    public String toString(){
        return departure + "(" + departureDateTime.getHour() +":"+ departureDateTime.getMinute() + ")" + " - " + arrival + " - " + price + "Euro";
     }



}
