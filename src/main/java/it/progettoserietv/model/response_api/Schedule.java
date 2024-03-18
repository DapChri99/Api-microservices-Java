package it.progettoserietv.model.response_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Schedule {

    @JsonProperty("time")
    private String time;
    @JsonProperty("days")
    private List<String> days;

}
