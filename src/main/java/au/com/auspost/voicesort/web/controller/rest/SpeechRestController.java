package au.com.auspost.voicesort.web.controller.rest;

import au.com.auspost.ame.web.value.AddressMatchRequestVO;
import au.com.auspost.ame.web.value.AddressMatchResponseVO;
import au.com.auspost.ame.web.value.AddressType;
import au.com.auspost.voicesort.service.AddressService;
import au.com.auspost.voicesort.service.SpeechParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/rest/api/speech")
public class SpeechRestController {
    @Autowired
    private SpeechParser speechParser;

    @Autowired
    private AddressService addressService;

    @Value("${voicesort.maxResults}")
    private Integer maxResults;

    @RequestMapping(method = RequestMethod.GET)
    public AddressMatchResponseVO list(@RequestParam(name ="text", required = true) String text,
                                       @RequestParam(name ="postCode", required = true) String postCode) {
        String search = speechParser.parse(text);

        AddressMatchResponseVO response = addressService.addressLookup(buildRequest(search, postCode, speechParser.isPhonetic(text)));

        return response;
    }

    private AddressMatchRequestVO buildRequest(String search, String postCode, boolean isPhonetic) {
        AddressMatchRequestVO request = new AddressMatchRequestVO()
                .putId(UUID.randomUUID())
                .putMaxResults(maxResults)
                .putPredictive(isPhonetic)
                .putFilters(new AddressMatchRequestVO.Filters()
                        .putAddressType(AddressType.THOROUGHFARE)
                )
                .putDetail(new AddressMatchRequestVO.Detail()
                        .putUnstructured(true)
                        .putDelivery(true)
                        .putGeo(true)
                )
                .addAddress(new AddressMatchRequestVO.Address()
                        .putId(UUID.randomUUID().toString())
                        .putText(search)
                        .putPostcode(postCode)
                );
        return request;
    }
}
