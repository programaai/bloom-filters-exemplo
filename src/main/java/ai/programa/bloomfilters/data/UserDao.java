package ai.programa.bloomfilters.data;

import java.util.List;
import java.util.Optional;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserDao {
  @SqlUpdate("INSERT INTO users (username) VALUES (?)")
  @GetGeneratedKeys
  long insert(String username);

  @SqlQuery("SELECT username FROM users")
  List<String> allUsernames();

  @SqlQuery("SELECT 1 FROM users WHERE username = ?")
  Optional<Long> exists(String username);

  @SqlUpdate("CREATE TABLE users ("
             + "id BIGINT AUTO_INCREMENT PRIMARY KEY, "
             + "username VARCHAR(255)"
             + ")")
  void createTable();
}
