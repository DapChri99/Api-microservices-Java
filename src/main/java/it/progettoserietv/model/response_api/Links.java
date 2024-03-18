package it.progettoserietv.model.response_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.progettoserietv.model.response_api.Link;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Links {

    @JsonProperty("self")
    private Link self;
    @JsonProperty("previousEpisode")
    private Link previousEpisode;
}
