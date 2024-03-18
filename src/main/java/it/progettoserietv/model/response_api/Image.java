package it.progettoserietv.model.response_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Image {

        @JsonProperty("medium")
        private String medium;
        @JsonProperty("original")
        private String original;
}
