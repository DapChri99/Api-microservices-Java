package it.progettoserietv.model.response_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class External {

    @JsonProperty("tvrage")
    private Object tvrage;
    @JsonProperty("thetvdb")
    private int thetvdb;
    @JsonProperty("imdb")
    private String imdb;

}
