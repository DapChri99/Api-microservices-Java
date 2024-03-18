package it.progettoserietv.model.response_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShowInfo {

        @JsonProperty("id")
        private int id;
        @JsonProperty("url")
        private String url;
        @JsonProperty("name")
        private String name;
        @JsonProperty("type")
        private String type;
        @JsonProperty("language")
        private String language;
        @JsonProperty("genres")
        private List<String> genres;
        @JsonProperty("status")
        private String status;
        @JsonProperty("runtime")
        private int runtime;
        @JsonProperty("averageRuntime")
        private int averageRuntime;
        @JsonProperty("premiered")
        private String premiered;
        @JsonProperty("ended")
        private String ended;
        @JsonProperty("officialSite")
        private String officialSite;
        @JsonProperty("schedule")
        private Schedule schedule;
        @JsonProperty("rating")
        private Rating rating;
        @JsonProperty("weight")
        private int weight;
        @JsonProperty("network")
        private Network network;
        @JsonProperty("webChanel")
        private String webChannel;
        @JsonProperty("dvdCountry")
        private String dvdCountry;
        @JsonProperty("externals")
        private External externals;
        @JsonProperty("image")
        private Image image;
        @JsonProperty("summary")
        private String summary;
        @JsonProperty("updated")
        private long updated;
        @JsonProperty("_links")
        private Links _links;
}
