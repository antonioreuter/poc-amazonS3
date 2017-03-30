package poc.amazons3.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 310280812 on 3/30/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataItemBlob implements Serializable {

    private Long id;

    @NotNull
    private Long dataItemId;

    @NotNull
    private String name;

    @NotNull
    private String type;

    @NotNull
    private byte[] data;

}
