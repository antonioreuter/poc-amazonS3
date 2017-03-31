package poc.amazons3.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poc.amazons3.models.DataItemBlob;

/**
 * Created by 310280812 on 3/30/2017.
 */
@Repository
public interface DataItemBlobRepository extends CrudRepository<DataItemBlob, Long>{

    DataItemBlob save(DataItemBlob dataItemBlob);

    DataItemBlob findById(Long id);
}
