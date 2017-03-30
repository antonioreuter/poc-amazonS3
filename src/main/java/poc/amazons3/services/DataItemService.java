package poc.amazons3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import poc.amazons3.models.DataItem;
import poc.amazons3.repositories.DataItemRepository;

import javax.transaction.Transactional;

/**
 * Created by 310280812 on 3/30/2017.
 */
@Service("dataItemServices")
public class DataItemService {

    @Autowired
    private DataItemRepository dataItemRepository;

    @Cacheable("dataItem~#id")
    @Transactional
    public DataItem save(DataItem dataItem) {
        return dataItemRepository.save(dataItem);
    }

    @CacheEvict("dataItem~#id")
    @Transactional
    public void delete(Long id) {
        dataItemRepository.delete(id);
    }

    @Cacheable("dataItem~#id")
    public DataItem findById(Long id) {
        return dataItemRepository.findOne(id);
    }
}
