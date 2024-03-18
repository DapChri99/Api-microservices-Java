package it.progettoserietv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GenericResponseDTO {

    @JsonProperty("esito")
    @Schema(description = "Operazione eseguita", requiredMode = Schema.RequiredMode.REQUIRED)
    private String esito;

    @JsonProperty("messaggio")
    @Schema(description = "Descrizione dell'operazione", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String messaggio;

}


