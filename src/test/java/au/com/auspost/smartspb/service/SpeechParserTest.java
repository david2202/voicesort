package au.com.auspost.smartspb.service;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class SpeechParserTest {
    private SpeechParser speechParser;

    @Before
    public void before() {
        speechParser = new SpeechParser();
    }

    @Test
    @DataProvider(value = {
            //text               expected
            "19 a m e          | 19 ame",
            "a m e             | ame",
            "19 a.m.e          | 19 ame",
            "3ze t             | 3 zet"
    },
            trimValues = true,
            splitBy = "\\|")
    public void test(String text, String expected) {
        String result = speechParser.parse(text);
        assertThat(result, is(expected));
    }
}
