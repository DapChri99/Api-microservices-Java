package it.progettoserietv.model.response_dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.progettoserietv.model.GenericResponseDTO;
import it.progettoserietv.model.TvInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetShowDTO extends GenericResponseDTO {

    @JsonProperty("serieTv")
    private TvInfoDTO tvInfoDTO;

    @JsonProperty("Lista di serie tv")
    private List<TvInfoDTO> tvInfoList;
}
