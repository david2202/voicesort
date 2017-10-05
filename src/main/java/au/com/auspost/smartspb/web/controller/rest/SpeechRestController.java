package au.com.auspost.smartspb.web.controller.rest;

import au.com.auspost.smartspb.service.AddressService;
import au.com.auspost.smartspb.service.SpeechParser;
import au.com.auspost.smartspb.web.value.AddressVO;
import au.com.auspost.smartspb.web.value.ame.AddressMatchRequestVO;
import au.com.auspost.smartspb.web.value.ame.AddressMatchResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
    public List<AddressVO> list(@RequestParam(name ="text", required = true) String text,
                                @RequestParam(name ="postCode", required = true) String postCode) {
        String search = speechParser.parse(text);

        AddressMatchResponseVO response = addressService.addressLookup(buildRequest(search, postCode, speechParser.isPhonetic(text)));

        List<AddressVO> addresses = buildResponse(response);
        return addresses;
    }

    private List<AddressVO> buildResponse(AddressMatchResponseVO addresses) {
        List<AddressVO> response = new ArrayList<>();
        for (AddressMatchResponseVO.Address address:addresses.getResults().get(0).getAddresses()) {
            AddressVO addressResponse = new AddressVO()
                    .putAddress(address.getUnstructured())
                    .putRoundId(address.getDeliveryData().getDeliveryOfficeRoundId())
                    .putConfidence(address.getConfidence().toString());
            response.add(addressResponse);
        }
        return response;
    }

    private AddressMatchRequestVO buildRequest(String search, String postCode, boolean isPhonetic) {
        AddressMatchRequestVO request = new AddressMatchRequestVO()
                .putId(UUID.randomUUID())
                .putMaxResults(maxResults)
                .putPredictive(isPhonetic)
                .putFilters(new AddressMatchRequestVO.Filters()
                        .putAddressType(AddressMatchRequestVO.Filters.AddressType.STREET)
                        .addPostcode(Integer.valueOf(postCode)))
                .putDetail(new AddressMatchRequestVO.Detail()
                        .putUnstructured(true)
                        .putDelivery(true)
                )
                .addAddress(new AddressMatchRequestVO.Address()
                        .putId(UUID.randomUUID().toString())
                        .putText(search)
                );
        return request;
    }
}
