package poc.amazons3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.amazons3.models.DataItemBlob;
import poc.amazons3.repositories.DataItemBlobRepository;

/**
 * Created by 310280812 on 3/30/2017.
 */
@Service
public class DataItemBlobService {

    @Autowired
    private DataItemBlobRepository dataItemBlobRepository;

    public Long save(DataItemBlob dataItemBlob) {
        return dataItemBlobRepository.save(dataItemBlob).getId();
    }

    public DataItemBlob findById(Long id) {
        return dataItemBlobRepository.findById(id);
    }
}
