package com.techforge.reservation.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.techforge.reservation",
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "transactionManager"
)
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource(Database database){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(database.getUrl());
        config.setUsername(database.getUsername());
        config.setPassword(database.getPassword());
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        config.setDriverClassName(database.getDriver());

        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, Database database){
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan("com.techforge.reservation.entity");
        emf.setDataSource(dataSource);

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(adapter);
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.community.dialect.MySQLLegacyDialect");
        properties.put("hibernate.hbm2ddl.auto", database.getDdl());
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.query.follow_on_locking", "true");
        emf.setJpaProperties(properties);

        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }

}
