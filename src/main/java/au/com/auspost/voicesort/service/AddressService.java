package au.com.auspost.voicesort.service;

import au.com.auspost.ame.web.value.AddressMatchRequestVO;
import au.com.auspost.ame.web.value.AddressMatchResponseVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {
    @Value("${voicesort.ame-api.url}")
    private String url;

    public AddressMatchResponseVO addressLookup(AddressMatchRequestVO request) {
        RestTemplate restTemplate = new RestTemplate();
        AddressMatchResponseVO response = restTemplate.postForObject(url, request, AddressMatchResponseVO.class);
        return response;
    }
}
