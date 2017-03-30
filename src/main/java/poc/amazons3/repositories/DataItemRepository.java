package poc.amazons3.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poc.amazons3.models.DataItem;

/**
 * Created by 310280812 on 3/30/2017.
 */
@Repository
public interface DataItemRepository extends CrudRepository<DataItem,Long> {



}
