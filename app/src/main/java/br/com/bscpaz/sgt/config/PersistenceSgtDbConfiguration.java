package br.com.bscpaz.sgt.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
    basePackages = "br.com.bscpaz.sgt.repositories.sgt", 
    entityManagerFactoryRef = "sgtEntityManagerFactory", 
    transactionManagerRef = "sgtTransactionManager"
)
public class PersistenceSgtDbConfiguration {
	
    @Autowired
    private Environment env;

	@Primary
    @Bean
    public PlatformTransactionManager sgtTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(sgtEntityManagerFactory().getObject());
        return transactionManager;
    }

	@Primary
    @Bean(name = "sgtEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sgtEntityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(sgtDataSource());
        em.setPackagesToScan("br.com.bscpaz.sgt.entities.sgt");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }
    
	@Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource sgtDataSource() {
        return DataSourceBuilder.create().build();
    }
}