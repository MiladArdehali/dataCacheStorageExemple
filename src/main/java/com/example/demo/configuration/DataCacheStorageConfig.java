package com.example.demo.configuration;

import com.digilion.dataAccessClass.enumerations.DacaStorageSupportEnum;
import com.digilion.dataAccessClass.manager.DacasManager;
import com.digilion.dataAccessClass.transaction.DacasTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Configuration of DataCacheStorage<br>
 *     <p>
 *         <div>
 *             Injection of DataCacheStorage and Build manager. Purge and storage support
 *         </div>
 *     </p>
 *
 * @author Digital Lion
 * @since 1.0
 */
@Configuration
public class DataCacheStorageConfig {

    /**
     * Definition of logger
     */
    Logger logger = LoggerFactory.getLogger(DataCacheStorageConfig.class);

    /**
     * Default constructor
     */
    public DataCacheStorageConfig() {
    }

    @Value("${dacas.file.path}")
    private String jsonFilePath;

    @Value("${dacas.max.object.in.memory}")
    private int limit;

    @Value("${dacas.max.time.to.live}")
    private Long ttl;

    /**
     * Run dacas manager
     */
    @Bean
    public DacasManager runDacas() {
        DacasManager dacasManager = new DacasManager(DacaStorageSupportEnum.FILE, limit, jsonFilePath, ttl);
        logger.info("Initializing Dacas.");
        return dacasManager;
    }

    @Scheduled(initialDelay = 1000*30, fixedDelay = 1000*60*30)
    private void purge() {
        DacasTransaction.purge();
    }

    @Scheduled(initialDelay = 1000*60, fixedDelay = 1000*60*60)
    private void autoPush(){
        DacasTransaction.push();
    }
}