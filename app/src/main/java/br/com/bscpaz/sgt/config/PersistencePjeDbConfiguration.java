package br.com.bscpaz.sgt.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableJpaRepositories (
    basePackages = "br.com.bscpaz.sgt.repositories.pje",
    entityManagerFactoryRef = "pjeEntityManagerFactory", 
    transactionManagerRef = "pjeTransactionManager"
)
public class PersistencePjeDbConfiguration {
	
    @Autowired
    private Environment env;

    @Bean
    public PlatformTransactionManager pjeTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(pjeEntityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean(name = "pjeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean pjeEntityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(pjeDataSource());
        em.setPackagesToScan("br.com.bscpaz.sgt.entities.pje");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }
    
    @Bean
    @ConfigurationProperties(prefix="spring.second-datasource")
    public DataSource pjeDataSource() {
        return DataSourceBuilder.create().build();
    }
}