package au.com.auspost.smartspb.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "voicesort")
public class SpeechParser {
    private HashMap<String, String> translations;
    private Map<String,String> translationsExpanded = new HashMap<>();

    public static final String ADDRESS_ALLOWABLE_REGEX = "[^a-zA-Z0-9 ']";

    private static final Logger LOGGER = LoggerFactory.getLogger(SpeechParser.class);

    @PostConstruct
    public void postConstruct() {
        translations.forEach((k,v)->{
            for (String s: StringUtils.split(k, ",")) {
                translationsExpanded.put(s, v);
            }
        });
    }

    public String parse(String text) {
        // Remove all foreign characters
        String cleaned = text.toLowerCase().replaceAll(ADDRESS_ALLOWABLE_REGEX, " ");

        String[] split = cleaned.split(" ");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < split.length; i++) {
            String[] subSplit = split[i].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
            for (String w:subSplit) {
                String word = translationsExpanded.get(w);
                if (word == null) {
                    word = w;
                }

                if (StringUtils.isNumeric(word)) {
                    sb.append(" ");
                }
                sb.append(word);
                if (StringUtils.isNumeric(word)) {
                    sb.append(" ");
                }
            }
        }
        LOGGER.info(sb.toString());
        return sb.toString();
    }

    public HashMap<String, String> getTranslations() {
        return translations;
    }

    public void setTranslations(HashMap<String, String> translations) {
        this.translations = translations;
    }
}
