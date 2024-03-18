package it.progettoserietv.model.response_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Rating {
    @JsonProperty("average")
    private Double average;
}
