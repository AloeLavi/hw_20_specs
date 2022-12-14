package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Support {
        private String url;
        private String text;
    }

