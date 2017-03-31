package poc.amazons3.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import poc.amazons3.models.DataItem;
import poc.amazons3.services.DataItemService;

import javax.validation.Valid;


/**
 * Created by 310280812 on 3/30/2017.
 */

@RequestMapping(value = "/api/dataitems", headers = { "api-version=1" })
@RestController
public class DataItemController {

    @Autowired
    private DataItemService dataItemService;

    @Qualifier("dataItemValidator")
    @Autowired
    private Validator dataItemValidator;

    @RequestMapping(method = RequestMethod.POST)
    public DataItem save(@Valid @RequestBody DataItem dataItem) {
        return dataItemService.save(dataItem);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public DataItem find(@PathVariable("id") Long id) {
        return dataItemService.findById(id);
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(dataItemValidator);
    }
}
