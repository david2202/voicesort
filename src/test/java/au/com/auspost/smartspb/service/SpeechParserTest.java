package au.com.auspost.smartspb.service;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class SpeechParserTest {
    private SpeechParser speechParser;

    @Before
    public void before() {
        speechParser = new SpeechParser();

        HashMap<String,String> translations = new HashMap<>();
        translations.put("alpha", "a");
        translations.put("bravo", "b");
        translations.put("charlie", "c");
        translations.put("to", "2");

        speechParser.setTranslations(translations);
        speechParser.postConstruct();
    }

    @Test
    @DataProvider(value = {
            //text                        expected
            "19 alpha bravo charlie     | 19 abc",
            "alpha bravo charlie        | abc",
            "19 a alpha bravo charlie   | 19a abc",
            "19a alpha bravo charlie    | 19a abc",
            "19 a westwood              | 19a westwood",
            "2 / 17a alpha bravo        | 2/ 17a ab",
            "2/17 alpha bravo           | 2/17 ab",
            "to union                   | 2 union",
            "2/17 a essex               | 2/17a essex"
    },
            trimValues = true,
            splitBy = "\\|")
    public void testParse(String text, String expected) {
        String result = speechParser.parse(text);
        assertThat(result, is(expected));
    }

    @Test
    @DataProvider(value = {
            //text               expected
            "19 alpha                 | false",
            "19 alpha fred            | false",
            "19 alpha bravo           | true",
            "19 alpha bravo charlie   | true"
    },
            trimValues = true,
            splitBy = "\\|")
    public void testIsPhonetic(String text, boolean expected) {
        boolean result = speechParser.isPhonetic(text);
        assertThat(result, is(expected));
    }
}
