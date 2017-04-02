package poc.amazons3.services.southamerica;

/**
 * Created by 310280812 on 3/31/2017.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import poc.amazons3.caching.CacheExpires;
import poc.amazons3.models.DataItem;
import poc.amazons3.repositories.DataItemRepository;
import poc.amazons3.services.DataItemService;

import javax.transaction.Transactional;

/**
 * Created by 310280812 on 3/30/2017.
 */
@Profile("south-america")
@Slf4j
@Service("dataItemServices")
public class DataItemServiceSouthAmerica implements DataItemService {

    @Autowired
    private DataItemRepository dataItemRepository;

    @Transactional
    public DataItem save(DataItem dataItem) {
        log.info("[Profile: {}] Saving... {}", "south-america", dataItem);
        return dataItemRepository.save(dataItem);
    }

    @CacheEvict(value = "dataItem~id", key = "#id")
    @Transactional
    public void delete(Long id) {
        log.info("[Profile: {}] deleting... {}", "south-america", id);
        dataItemRepository.delete(id);
    }

    @Cacheable(value = "dataItem~id", key = "#id")
    @CacheExpires(expires = 60L)
    public DataItem findById(Long id) {
        log.info("[Profile: {}] FindById... {}", "south-america", id);
        return dataItemRepository.findOne(id);
    }
}

