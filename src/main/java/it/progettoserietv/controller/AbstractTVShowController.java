package it.progettoserietv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.progettoserietv.exception.DeleteShowException;
import it.progettoserietv.model.GenericResponseDTO;
import it.progettoserietv.model.response_dto.GetShowDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


public abstract class AbstractTVShowController {

        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Operazione eseguita positivamente", content = @Content(schema = @Schema(implementation = GetShowDTO.class))),
                @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))),
                @ApiResponse(responseCode = "500", description = "Errore interno al server", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class)))
        })
        @Operation(description = "API che cerca nel db una serie Tv con il nome passato")
        @GetMapping("/get")
        abstract ResponseEntity<GenericResponseDTO> getShowByName(@Parameter(description = "nome della serie tv da cercare", required = true) @RequestParam String showName);

        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Operazione eseguita positivamente", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))),
                @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))),
                @ApiResponse(responseCode = "500", description = "Errore interno al server", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class)))
        })
        @Operation(description = "API per salvare le informazioni riguardanti una serie Tv")
        @PostMapping("/show")
        abstract ResponseEntity<GenericResponseDTO> postShowData (@Parameter(description = "nome della serie tv", required = true) @RequestBody @Valid String showName);

        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Operazione eseguita positivamente", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))),
                @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))),
                @ApiResponse(responseCode = "500", description = "Errore interno al server", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class)))
        })
        @Operation(description = "API per eliminare la serie Tv che corrisponde al nome fornito ")
        @DeleteMapping("/elimina")
        abstract ResponseEntity<GenericResponseDTO> deleteByShowName(@Parameter(description = "nome della serie tv da eliminare", required = true) @RequestParam String showName);


        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Operazione eseguita positivamente", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))),
                @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))),
                @ApiResponse(responseCode = "500", description = "Errore interno", content = @Content(schema = @Schema(implementation = GenericResponseDTO.class)))
        })
        @Operation(description = "API per recuperare tutte le serie Tv per lingua")
        @GetMapping("/getAll")
        abstract ResponseEntity<GenericResponseDTO> getAllByLanguage(@Parameter(description = "lingua da cercare", required = true) @RequestParam String language);
        }



