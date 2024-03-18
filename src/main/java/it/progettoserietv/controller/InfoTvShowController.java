package it.progettoserietv.controller;

import it.progettoserietv.model.GenericResponseDTO;
import it.progettoserietv.model.TvInfoDTO;
import it.progettoserietv.model.response_dto.GetShowDTO;
import it.progettoserietv.service.TvShowService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@Slf4j
public class InfoTvShowController extends AbstractTVShowController {

    @Autowired
    private TvShowService tvShowService;

    @GetMapping("/get")
    public ResponseEntity getShowByName(@RequestParam String name) {
        log.info("Method [getShowByName] START");
        log.debug("Response body {}", name);

        GenericResponseDTO genericResponseDTO = new GenericResponseDTO();

        try {
            GetShowDTO getShowDTO = new GetShowDTO();
            TvInfoDTO tvInfoDTO = tvShowService.getTvShowByName(name);
            log.debug("Response body {}", tvInfoDTO);

            getShowDTO.setTvInfoDTO(tvInfoDTO);
            getShowDTO.setEsito("OK");
            return new ResponseEntity<>(getShowDTO, HttpStatus.OK);

        } catch (Exception ex) {
            log.error("Error while trying to get show data", ex);
            genericResponseDTO.setEsito("KO");
            genericResponseDTO.setMessaggio(ex.getMessage());
            return new ResponseEntity<>(genericResponseDTO, HttpStatus.BAD_REQUEST);
        }
        finally {
            log.info("Method [getShowByName] END");
        }
    }

    @PostMapping("/show")
    public ResponseEntity postShowData(@RequestBody @Valid String showName) {
        log.info("Method [postShowData] START");
        log.debug("Response body {}", showName);

        GenericResponseDTO genericResponseDTO = new GenericResponseDTO();

        try {
            TvInfoDTO tvInfoDTO = tvShowService.postShowInfo(showName);
            genericResponseDTO.setEsito("OK");
            genericResponseDTO.setMessaggio("la risorsa è stata salvata con successo");
            log.debug("Response body {}", genericResponseDTO, tvInfoDTO);

            return new ResponseEntity<>(genericResponseDTO, HttpStatus.OK);

        }catch ( Exception ex) {
            log.error("Error while trying to get show data", ex);
            genericResponseDTO.setEsito("KO");
            genericResponseDTO.setMessaggio(ex.getMessage());
            return new ResponseEntity<>(genericResponseDTO, HttpStatus.BAD_REQUEST);
        }

        finally {
            log.info("Method [postShowData] END");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteByShowName (@RequestParam String showName) {
        log.info("Method [deleteByShowName] START");
        GenericResponseDTO genericResponseDTO = new GenericResponseDTO();

        try {
            tvShowService.deleteByName(showName);
            genericResponseDTO.setEsito("OK");
            genericResponseDTO.setMessaggio("la risorsa è stata eliminata con successo");
            log.debug("Response body {}", genericResponseDTO);

            return new ResponseEntity<>(genericResponseDTO, HttpStatus.OK);

        }catch (Exception e){
            log.error("Error while trying to get show data", e);
            genericResponseDTO.setEsito("KO");
            genericResponseDTO.setMessaggio(e.getMessage());
            return new ResponseEntity<>(genericResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllByLanguage(@RequestParam String language){
        log.info("Method [getAllByLanguage] START");
        log.debug("Lingua passata come parametro : {}", language);
        GenericResponseDTO genericResponseDTO = new GenericResponseDTO();


        try {
            List<TvInfoDTO> tvListDto = tvShowService.getAllByLang(language);
            log.debug("Response body: {}", tvListDto);

            if (!tvListDto.isEmpty()) {
                return  new ResponseEntity<>(tvListDto, HttpStatus.OK);
            } else {

                genericResponseDTO.setEsito("KO");
                genericResponseDTO.setMessaggio("Nessuna serie TV trovata con la lingua specificata");
                return new ResponseEntity<>(genericResponseDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.error("Errore durante la ricerca delle serie Tv", ex);
            genericResponseDTO.setEsito("KO");
            genericResponseDTO.setMessaggio("Errore durante il recupero delle serie TV per lingua");
            return new ResponseEntity<>(genericResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            log.info("Method [getAllTvShowsByLanguage] END");
        }
    }
}




