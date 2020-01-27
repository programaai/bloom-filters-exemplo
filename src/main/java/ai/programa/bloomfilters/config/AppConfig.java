package ai.programa.bloomfilters.config;

import ai.programa.bloomfilters.data.UserDao;
import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.spring4.JdbiFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public JdbiFactoryBean jdbiFactoryBean(DataSource dataSource) {
    return new JdbiFactoryBean(dataSource)
        .setAutoInstallPlugins(true);
  }

  @Bean
  public UserDao userDao(Jdbi jdbi) {
    return jdbi.onDemand(UserDao.class);
  }
}
