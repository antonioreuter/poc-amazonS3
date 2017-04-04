package poc.amazons3.monitoring;

/**
 * Created by 310280812 on 4/4/2017.
 */
public interface Probe {

    /**
     * @return get the name of the probe.
     */
    String getName();

    /**
     * Checks if the "information" monitored by the probe is ok.
     *
     * @return the result of the check.
     */
    ProbeResult check();

}