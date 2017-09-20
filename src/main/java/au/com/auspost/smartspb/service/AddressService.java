package au.com.auspost.smartspb.service;

import au.com.auspost.smartspb.web.value.ame.AddressMatchRequestVO;
import au.com.auspost.smartspb.web.value.ame.AddressMatchResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

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
