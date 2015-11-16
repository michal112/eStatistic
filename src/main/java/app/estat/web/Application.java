package app.estat.web;

import app.estat.web.model.RepositoryConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class);
        //RepositoryConfiguration.setCurrentDataSourceType(RepositoryConfiguration.DataSourceType.TEST);
    }

    public static class Constant {

        public static final String TABLE_COW = "cow";
        public static final String TABLE_COW_PARENT = "cow_parent";
        public static final String TABLE_LACTATION = "lactation";
        public static final String TABLE_INSEMINATION = "insemination";
        public static final String TABLE_BULL = "bull";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_BIRTH = "birth";
        public static final String COLUMN_BOOK = "book";

        public static final String COLUMN_COW_PARENT = "cow_parent";
        public static final String COLUMN_LACTATION_COW = "lactation_cow";
        public static final String COLUMN_INSEMINATION_BULL = "insemination_bull";
        public static final String COLUMN_INSEMINATION_COW = "insemination_cow";

    }

}
