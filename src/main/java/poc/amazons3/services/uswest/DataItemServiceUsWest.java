package poc.amazons3.services.uswest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import poc.amazons3.models.DataItem;
import poc.amazons3.repositories.DataItemRepository;
import poc.amazons3.services.DataItemService;

import javax.transaction.Transactional;

/**
 * Created by 310280812 on 3/30/2017.
 */
@Profile("us-west")
@Slf4j
@Service("dataItemServices")
public class DataItemServiceUsWest implements DataItemService {

    @Autowired
    private DataItemRepository dataItemRepository;

    @Cacheable("dataItem~#id")
    @Transactional
    public DataItem save(DataItem dataItem) {
        log.info("[Profile: {}] Saving... {}", "us-west", dataItem);
        return dataItemRepository.save(dataItem);
    }

    @CacheEvict("dataItem~#id")
    @Transactional
    public void delete(Long id) {
        log.info("[Profile: {}] deleting... {}", "us-west", id);
        dataItemRepository.delete(id);
    }

    @Cacheable("dataItem~#id")
    public DataItem findById(Long id) {
        log.info("[Profile: {}] FindById... {}", "us-west", id);
        return dataItemRepository.findOne(id);
    }
}
