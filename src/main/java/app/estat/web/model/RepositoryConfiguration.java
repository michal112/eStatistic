package app.estat.web.model;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RepositoryConfiguration {

    public enum DataSourceType {
        PRIMARY,
        TEST
    }

    private static DataSourceType CURRENT_DATA_SOURCE_TYPE;

    public static void setCurrentDataSourceType(DataSourceType dataSourceType) {
        CURRENT_DATA_SOURCE_TYPE = dataSourceType;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource getPrimaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource getTestDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public AbstractRoutingDataSource getRoutingDataSource() {
        AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                return CURRENT_DATA_SOURCE_TYPE;
            }
        };
        routingDataSource.setTargetDataSources(getDataSourcesMap());
        routingDataSource.setDefaultTargetDataSource(getPrimaryDataSource());

        return routingDataSource;
    }

    private Map<Object, Object> getDataSourcesMap() {
        Map<Object, Object> dataSourcesMap = new HashMap<>();

        dataSourcesMap.put(DataSourceType.PRIMARY, getPrimaryDataSource());
        dataSourcesMap.put(DataSourceType.TEST, getTestDataSource());

        return dataSourcesMap;
    }

}
