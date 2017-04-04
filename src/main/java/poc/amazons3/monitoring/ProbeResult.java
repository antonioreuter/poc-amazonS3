package poc.amazons3.monitoring;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by 310280812 on 4/4/2017.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(doNotUseGetters = false, of = {"name", "kind"})
public class ProbeResult implements Serializable {

    @JsonProperty(value = "name", index = 1)
    private String name;

    @JsonProperty(value = "kind", index = 2)
    private String kind;

    @JsonProperty(value = "status", index = 3)
    private boolean status;

    @JsonProperty(value = "time", index = 4)
    private double time;

    @JsonProperty(value = "optional", index = 5)
    private boolean optional;

    @JsonProperty(value = "details", index = 6)
    private Map<String, String> details;

    @JsonProperty(value = "error", index = 7)
    private String error;
}
