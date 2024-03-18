package it.progettoserietv.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class TvInfoDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "url",
            "name",
            "language",
            "status",
    })

    @JsonProperty("url")
    @Schema(description = "url della serie tv", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @NotBlank (message = "url della serie tv non può essere empty o null")
    private String url;

    @JsonProperty("name")
    @Schema(description = "nome della serie tv", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank (message = "nome della serie tv non può essere empty o null")
    private String name;

    @JsonProperty("language")
    @Schema(description = "lingua nativa della serie tv", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @NotBlank (message = "lingua della serie tv non può essere empty o null")
    private String language;

    @JsonProperty("status")
    @Schema(description = "stato della serie tv", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @NotBlank (message = "status della serie tv non può essere empty o null")
    private String status;
}
