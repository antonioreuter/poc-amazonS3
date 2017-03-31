package poc.amazons3.services;

import poc.amazons3.models.DataItem;

/**
 * Created by 310280812 on 3/31/2017.
 */
public interface DataItemService {
    DataItem save(DataItem dataItem);

    void delete(Long id);

    DataItem findById(Long id);
}
