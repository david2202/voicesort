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

import static org.apache.commons.lang3.StringUtils.*;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "voicesort")
public class SpeechParser {
    private HashMap<String, String> translations;
    private Map<String,String> translationsExpanded = new HashMap<>();

    public static final String ADDRESS_ALLOWABLE_REGEX = "[^a-zA-Z0-9 /']";

    private static final Logger LOGGER = LoggerFactory.getLogger(SpeechParser.class);

    @PostConstruct
    public void postConstruct() {
        translations.forEach((k,v)->{
            for (String s: split(k, ",")) {
                translationsExpanded.put(s, v);
            }
        });
    }

    public String parse(String text) {
        // Remove all foreign characters
        String cleaned = text.toLowerCase().replaceAll(ADDRESS_ALLOWABLE_REGEX, " ").trim();

        StringBuilder sb = new StringBuilder();

        String[] tokens = split(cleaned);
        String[] translatedTokens = new String[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            translatedTokens[i] = translationsExpanded.get(tokens[i]);
            if (translatedTokens[i] == null) {
                translatedTokens[i] = tokens[i];
            }
            if (isNumeric(translatedTokens[i])) {
                sb.append(translatedTokens[i]);
            } else if (tokens[i].length() == 1) {
                if (i > 0 && translatedTokens[i-1].matches(".*\\d.*")) {
                    sb.append(translatedTokens[i]);
                    sb.append(" ");
                }
            } else {
                if (i > 0 && translatedTokens[i-1].matches(".*\\d.*")) {
                    sb.append(" ");
                }
                sb.append(translatedTokens[i]);
            }
        }

        String result = sb.toString().trim();
        LOGGER.info(result);
        return result;
    }

    public boolean isPhonetic(String text) {
        String[] tokens = split(text);
        boolean foundFirst = false;
        for (String token:tokens) {
            if (translationsExpanded.containsKey(token)) {
                if (foundFirst) {
                    return true;
                } else {
                    foundFirst = true;
                }
            }
        }
        return false;
    }

    public HashMap<String, String> getTranslations() {
        return translations;
    }

    public void setTranslations(HashMap<String, String> translations) {
        this.translations = translations;
    }
}
