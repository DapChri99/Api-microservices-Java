package it.progettoserietv.model.response_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.progettoserietv.model.response_api.Country;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Network {

        @JsonProperty("id")
        private int id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("country")
        private Country country;
        @JsonProperty("officialSite")
        private String officialSite;
}
