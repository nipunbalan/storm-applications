package storm.applications.spout.generator;

import backtype.storm.tuple.Values;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import storm.applications.constants.SpikeDetectionConstants;
import storm.applications.constants.SpikeDetectionConstants.Conf;
import storm.applications.util.ConfigUtility;
import storm.applications.util.StreamValues;

/**
 *
 * @author Maycon Viana Bordin <mayconbordin@gmail.com>
 */
public class SensorGenerator extends Generator {
    private long count;  
    private String deviceID;
    private final Random random = new Random();
    
    @Override
    public void initialize(Map config) {
        super.initialize(config);
        
        count = ConfigUtility.getLong(config, Conf.GENERATOR_COUNT, 1000000);
        deviceID = RandomStringUtils.randomAlphanumeric(20);
    }

    @Override
    public StreamValues generate() {
        if (count-- > 0) {
            return new StreamValues(deviceID, (random.nextDouble() * 10) + 50);                        
        } else if (count-- == -1) {
            return new StreamValues(new Values(deviceID, -1.0));
        }
        
        return null;
    }
    
}
