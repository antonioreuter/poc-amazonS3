package poc.amazons3.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import poc.amazons3.models.DataItemBlob;
import poc.amazons3.repositories.DataItemBlobRepository;

/**
 * Created by 310280812 on 3/30/2017.
 */

@RestController
@RequestMapping(value = "/api/dataitemblobs", headers = { "api-version=1" })
public class DataItemBlobController {

    @Autowired
    private DataItemBlobRepository dataItemBlobRepository;


    @RequestMapping(method = RequestMethod.POST)
    public Long save(@RequestBody DataItemBlob dataItemBlob) {
        return dataItemBlobRepository.save(dataItemBlob);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public DataItemBlob findById(@PathVariable("id") Long id) {
        return dataItemBlobRepository.findById(id);
    }

}
