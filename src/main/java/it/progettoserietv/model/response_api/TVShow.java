package it.progettoserietv.model.response_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)  // ignora il campo "score" durnte il mapping json a pojo
public class TVShow {

    @JsonProperty("score")
    private double score;

    @JsonProperty("show")
    private ShowInfo show;
}
