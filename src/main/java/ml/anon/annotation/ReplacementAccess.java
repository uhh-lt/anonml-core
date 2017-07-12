package ml.anon.annotation;

import ml.anon.model.anonymization.Label;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;

/**
 * Created by mirco on 13.06.17.
 */
public class ReplacementAccess {

    private RestTemplate template = new RestTemplate();

    public String generateReplacement(String original, Label label) throws UnsupportedEncodingException {
        String l = label == null ? "MISC" : label.toString();

        StringBuilder builder = new StringBuilder("http://127.0.0.1:9001/replacement").append("?original=")
                .append(UriUtils.encode(original, "UTF-8")).append("&label=").append(l);
        String url = builder.toString();

        ResponseEntity<String> repl = template.getForEntity(url, String.class);
        return repl.getBody();
    }
}