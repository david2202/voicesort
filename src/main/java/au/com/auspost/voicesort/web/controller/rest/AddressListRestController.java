package au.com.auspost.voicesort.web.controller.rest;

import au.com.auspost.voicesort.dao.AddressListDao;
import au.com.auspost.voicesort.domain.AddressList;
import au.com.auspost.voicesort.domain.AddressListItem;
import au.com.auspost.voicesort.service.AddressListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/rest/api/addressList")
public class AddressListRestController {

    @Autowired
    private AddressListService addressListService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AddressList get(@PathVariable("id") int id) {
        return addressListService.load(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public AddressList find(@RequestParam("postCode") Integer postCode,
                            HttpServletResponse response) {
        AddressList addressList = addressListService.findByPostCodeAndStatusCd(postCode, AddressList.Status.OPEN);
        if (addressList == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return addressList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public AddressList save(@RequestBody AddressList addressList) {
        addressListService.save(addressList);
        return addressList;
    }

    @RequestMapping(value = "/{addressListId}/addressListItem", method = RequestMethod.POST)
    public AddressListItem saveItem(@PathVariable("addressListId") int addressListId,
                                    @RequestBody AddressListItem addressListItem) {
        AddressList addressList = addressListService.load(addressListId);
        addressListItem.setAddressList(addressList);
        addressListService.saveItem(addressListItem);
        return addressListItem;
    }

    @RequestMapping(value = "/{addressListId}/addressListItem/{addressListItemId}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable("addressListId") int addressListId,
                                    @PathVariable("addressListItemId") int addressListItemId) {
        addressListService.deleteItem(addressListItemId);
    }
}
