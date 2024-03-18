package it.progettoserietv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.progettoserietv.entity.Tbl_tv_show;
import it.progettoserietv.exception.*;
import it.progettoserietv.model.response_api.TVShow;
import it.progettoserietv.model.TvInfoDTO;
import it.progettoserietv.repository.TvShowRepository;
import it.progettoserietv.restTemplate.TVMazeApiRestCaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TvShowService {
    @Autowired
    private TVMazeApiRestCaller tvMazeCall;
    @Autowired
    private TvShowRepository tvShowRepository;
    public TvInfoDTO postShowInfo( String showName ) throws Exception {
        log.info("Method [postShowData] START");
        TvInfoDTO tvInfoDTO;
        boolean trovata;

        // Cerco la risorsa nel DB

        tvInfoDTO = getTvShow(showName);
        if (tvInfoDTO != null && tvInfoDTO.getName() != null && tvInfoDTO.getName().equalsIgnoreCase(showName)) {
                log.info("Risorsa ottenuta dal DB {} : ", tvInfoDTO);
                trovata = true;
        }
        else {
            trovata = false;
        }
        // In caso trovata = false allora chiamo l'API TvMaze
        try{
            if( !trovata) {
                String responseApi = tvMazeCall.callApi(showName);
                String response = extractResponse(responseApi);
                tvInfoDTO = mapToTvInfoDTO(response);
                log.trace("Risorsa ottenuta da TVMaze {}", tvInfoDTO);
            }
        }catch(Exception ex){
            log.error("Errore nella richiesta all'API TvMaze {}", ex.getMessage() , ex);
            throw new TvMazeResponseException("E' stato impossibile recuperare la risorsa ottenuta da TVMaze", ex);
        }

        try{
            if(!trovata) {
                saveInfoShow(tvInfoDTO);
                log.trace("Risorsa ottenuta da TVMaze e salvata {}", tvInfoDTO);
            }
        }catch( Exception ex) {
            log.error("Sembra che ci sia stato un problema nel processare la risposta dall'API di TVMaze {}", ex);
            throw new DbSaveInfoException("E' stato impossibile salvare la risorsa ottenuta da TVMaze");
        }
        log.info("Method [getShowData] END");
        return tvInfoDTO;
    }

    public TvInfoDTO getTvShowByName( String showName ) {
        log.info("Method [getTvShowByName] START");
        TvInfoDTO tvInfoDTO = new TvInfoDTO();
        try{
            Tbl_tv_show tbl_tv_show = tvShowRepository.findByNameIgnoreCase(showName);
            if(tbl_tv_show!= null && tbl_tv_show.getName().equalsIgnoreCase(showName)){
                log.info("Risorsa trovata nel DB {}", tbl_tv_show);
                tvInfoDTO = mapperEntityToDTO(tbl_tv_show);
                log.trace("Serie Tv {} trovata nel DB", tvInfoDTO);
                return tvInfoDTO;

            }else {
                log.info("Risorsa non trovata nel DB");
                throw new GetShowException("Serie non trovata nel DB");
            }
        }catch (Exception ex) {
            log.error("Errore nel cercare la risorsa nel DB {}", ex.getMessage());
            throw new GetShowException("Errore nel cercare la risorsa nel DB");
        }
    }

    public void deleteByName (String showName) {
        log.info("Method [deleteByName] START");
        try{
            Tbl_tv_show tbl_tv_show = tvShowRepository.findByNameIgnoreCase(showName);

            if( tbl_tv_show != null && tbl_tv_show.getName().equalsIgnoreCase(showName)) {
                tvShowRepository.delete(tbl_tv_show);
            }else {
                log.info("Risorsa non trovata nel DB");
                throw new DeleteShowException("Risorsa non trovata nel DB");
            }

        }catch(Exception ex) {
            log.error("Errore cancellazione DB {}", ex.getMessage());
            throw new DeleteShowException("Errore durante l'eliminazione della serie Tv");
        }
    }
    public List<TvInfoDTO> getAllByLang (String language) {
        log.info("Method [getAllByLang] START");
        List<TvInfoDTO> tvInfoDTOList = new ArrayList<TvInfoDTO>();

        try {
            List<Tbl_tv_show> tvListTbl = tvShowRepository.findByLanguageIgnoreCase(language);
            if(!tvListTbl.isEmpty()) {
                log.info("Risorse trovati nel DB {}", tvListTbl);
                for (Tbl_tv_show tbl_tv_show : tvListTbl) {
                    TvInfoDTO tvInfoDTO = mapperEntityToDTO(tbl_tv_show);
                    tvInfoDTOList.add(tvInfoDTO);
                }log.trace("Serie Tv trovate nel DB con lingua '{}' : {}", language,tvInfoDTOList);
            }else{
                log.info("Nessuna serie Tv trovata nel DB con lingua '{}'", language);
            }

        }catch (Exception ex) {
            log.error("Errore nel recuperare le risorse Tv con lingua '{}'", language);
            throw new GetShowException("Errore nel recuperare le risorse Tv con lingua" +language);
        }
        log.info("Method [getAllByLang] END");
        return tvInfoDTOList;
    }










    private void saveInfoShow(TvInfoDTO tvInfoDTO) {
        log.info("Method [saveInfoShow] START");
        try{
            if (tvInfoDTO!= null && tvInfoDTO.getName()!= null) {

                Tbl_tv_show tbl_tv_show = mapperDTOToEntity(tvInfoDTO);
                tvShowRepository.save(tbl_tv_show);
                log.trace("Risorsa salvata {}", tvInfoDTO);

            }else {
                log.trace("Errore salvataggio DB; Alcuni campi essenziali sono nulli");
                throw new DbSaveInfoException("Alcuni campi essenziali sono nulli");
            }
        }catch (NullPointerException ex){
            log.error("Errore salvataggio DB {}", ex);
            throw new DbSaveInfoException("Impossibile salvare la risorsa ottenuta: Potrebbe non esistere");
        }
    }

    private TvInfoDTO getTvShow(String showName) {
        log.info("Method [getTvShow] START");
        TvInfoDTO tvInfoDTO = new TvInfoDTO();

        try {
            Tbl_tv_show tbl_tv_show = tvShowRepository.findByNameIgnoreCase(showName);
            if(tbl_tv_show != null && tbl_tv_show.getName().equalsIgnoreCase(showName)){
                log.trace("Risorsa trovata nel DB {}", tbl_tv_show);
                return tvInfoDTO = mapperEntityToDTO(tbl_tv_show);
            }
        }catch (Exception ex) {
            log.error("Eccezione del DB {}", ex.getMessage());
        }
        log.info("Method [getTvShow] END");
        return tvInfoDTO;
    }

    private String extractResponse(String response) throws Exception {
        log.info("Method [extractResponse] START");
        try {

            int stringEnding = response.toString().length() - 1;
            return response.toString().substring(1, stringEnding);

        }catch (Exception ex) {
            log.error("Errore estrazione risorsa {}", ex);
            throw new Exception ("IL body della risposta ricevuta da TVMaze è vuoto ");
        }
    }

    // Usa ObjectMapper (Jackson==) per deserializzare il JSON in un oggetto ed estrae le informazioni rilevanti per creare un TvInfoDTO.
    private TvInfoDTO mapToTvInfoDTO(String response) {
        log.info("Method [mapToTvInfoDTO] START");
        try {
            if(!response.isBlank()) {
                ObjectMapper objectMapper = new ObjectMapper();
                TVShow tvShow = objectMapper.readValue(response, TVShow.class);
                return new TvInfoDTO(tvShow.getShow().getUrl(), tvShow.getShow().getName(), tvShow.getShow().getLanguage(), tvShow.getShow().getStatus());
            }

        } catch (Exception ex) {
                log.error("Errore mapping {} : ", ex.getMessage(), ex);
                throw new MappingException ("Errore di mappaggio dei dati " , ex);
        }
        return new TvInfoDTO();
    }
    private TvInfoDTO mapperEntityToDTO(Tbl_tv_show tbl_tv_show) {
        log.info("Method [mapperEntityToDTO] START");
        TvInfoDTO tvInfoDto = new TvInfoDTO();
        try {
            if(tbl_tv_show != null) {
                tvInfoDto.setUrl(tbl_tv_show.getUrl());
                tvInfoDto.setName(tbl_tv_show.getName());
                tvInfoDto.setLanguage(tbl_tv_show.getLanguage());
                tvInfoDto.setStatus(tbl_tv_show.getStatus());
                log.info("Risorsa mappata da entity a dto ", tvInfoDto);
            }

        }catch (Exception ex) {
            log.error("Errore durante il mapping o risorsa non presente nel DB {} : ", ex);
            throw new MappingException ("Errore di mappaggio dei dati " , ex);
        }
        return tvInfoDto;
    }
    private Tbl_tv_show mapperDTOToEntity(TvInfoDTO tvInfoDTO) {
        log.info("Method [mapperDTOToEntity] START");
        Tbl_tv_show tbl_tv_show = new Tbl_tv_show();
        try {
            if(tvInfoDTO!= null) {
                tbl_tv_show.setUrl(tvInfoDTO.getUrl());
                tbl_tv_show.setName(tvInfoDTO.getName());
                tbl_tv_show.setLanguage(tvInfoDTO.getLanguage());
                tbl_tv_show.setStatus(tvInfoDTO.getStatus());
                return tbl_tv_show;
            }
        }catch(Exception e){
            log.error("Errore mapping {} : ", e.getMessage(), e);
            throw new MappingException ("Errore di mappaggio dei dati ", e);
        }
        return tbl_tv_show;
    }
}

