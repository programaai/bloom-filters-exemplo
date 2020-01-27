package ai.programa.bloomfilters;

import ai.programa.bloomfilters.data.UserDao;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class App implements CommandLineRunner,
                            ApplicationContextAware {

  private ApplicationContext applicationContext;

  public static void main(String[] args) {
    new SpringApplication(App.class).run(args);
  }

  @Override
  public void run(final String... args) {
    final UserDao userDao = applicationContext.getBean(UserDao.class);
    userDao.createTable();
  }

  @Override
  public void setApplicationContext(final ApplicationContext ctx)
      throws BeansException {
    this.applicationContext = ctx;
  }
}
