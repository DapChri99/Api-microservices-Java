package it.progettoserietv.restTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("tvMazeApiRestClient")
@Slf4j
public class TVMazeApiRestCaller {

    @Value("${api.tvMaze.url}")
    private String tvMazeUrl;

    //  Prende l'URL base e la stringa dell'utente, compone l'URL finale e utilizza RestTemplate per fare la richiesta GET all'URL.
    public String callApi(String showName) throws Exception {
        log.info("callApi [START]");
        String searchUrl = tvMazeUrl + showName;
//        ResponseEntity responseEntity = null;

        RestTemplate restTemplate = new RestTemplate();

        try {
            // Esecuzione della richiesta GET all'API TVMaze'
            ResponseEntity<String> response = restTemplate.getForEntity(searchUrl, String.class);
            log.info("Response from Tv Maze {}", response);
            log.info("callApi [END]");
            return response.getBody();

        } catch (Exception ex) {
            log.error("Eccezione restTemplate {}", ex);
            throw new Exception("Eccezione restTemplate {}", ex);
        }

    }
}



