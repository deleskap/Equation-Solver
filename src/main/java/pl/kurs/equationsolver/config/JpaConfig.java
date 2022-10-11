package pl.kurs.equationsolver.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean createEmf(JpaVendorAdapter adapter, DataSource dataSource){
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setJpaVendorAdapter(adapter);
        emf.setPackagesToScan("pl.kurs.equationsolver.model");
        emf.setDataSource(dataSource);
        return emf;
    }

   @Profile("prod")
    @Bean
    public JpaVendorAdapter createVendorAdapterProd(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true); //"drop-and-create"
        return adapter;
    }

    @Profile("prod")
    @Bean
    public DataSource createDataSourceProd(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/equation_solver?useSSL=false&serverTimezone=UTC");
        ds.setPassword("root");
        ds.setUsername("root");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setInitialSize(5);
        return ds;
    }

    @Profile("dev")
    @Bean
    public JpaVendorAdapter createVendorAdapterDev(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true); //"drop-and-create"
        return adapter;
    }

    @Profile("dev")
    @Bean
    public DataSource createDataSourceDev(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:h2:mem:testdb");
        ds.setUsername("sa");
        ds.setPassword("");
        ds.setDriverClassName("org.h2.Driver");
        ds.setInitialSize(5);
        return ds;
    }

}
