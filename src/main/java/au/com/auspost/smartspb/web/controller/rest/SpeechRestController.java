package au.com.auspost.smartspb.web.controller.rest;

import au.com.auspost.smartspb.service.AddressService;
import au.com.auspost.smartspb.service.SpeechParser;
import au.com.auspost.smartspb.web.value.AddressVO;
import au.com.auspost.smartspb.web.value.ame.AddressMatchRequestVO;
import au.com.auspost.smartspb.web.value.ame.AddressMatchResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.GET)
    public List<AddressVO> list(@RequestParam(name ="text", required = true) String text) {
        String search = speechParser.parse(text);

        AddressMatchResponseVO response = addressService.addressLookup(buildRequest(search));

        List<AddressVO> addresses = buildResponse(response);
        return addresses;
    }

    private List<AddressVO> buildResponse(AddressMatchResponseVO addresses) {
        List<AddressVO> response = new ArrayList<>();
        for (AddressMatchResponseVO.Address address:addresses.getResults().get(0).getAddresses()) {
            AddressVO addressResponse = new AddressVO();
            addressResponse.setAddress(address.getUnstructured());
            addressResponse.setRoundId(address.getDeliveryData().getDeliveryOfficeRoundId());
            response.add(addressResponse);
        }
        return response;
    }

    private AddressMatchRequestVO buildRequest(String search) {
        AddressMatchRequestVO request = new AddressMatchRequestVO()
                .putId(UUID.randomUUID())
                .putMaxResults(5)
                .putPredictive(true)
                .putFilters(new AddressMatchRequestVO.Filters()
                        .putAddressType(AddressMatchRequestVO.Filters.AddressType.STREET)
                        .addPostcode(3127))
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
