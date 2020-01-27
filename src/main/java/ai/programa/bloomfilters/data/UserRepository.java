package ai.programa.bloomfilters.data;

import ai.programa.bloomfilters.BloomFilter;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {
  private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class);

  private final UserDao dao;
  private final BloomFilter bloomFilter;

  @Autowired
  public UserRepository(UserDao dao) {
    this.dao = dao;
    bloomFilter = new BloomFilter(10, 0.1);
  }

  public List<String> all() {
    return dao.allUsernames();
  }

  public long create(String username) {
    final long userId = dao.insert(username);
    bloomFilter.add(username);
    return userId;
  }

  public boolean exists(String username) {
    if (bloomFilter.exists(username)) {
      LOG.info("[USERNAME: {}] PODE EXISTIR NA COLECAO", username);
      final Optional<Long> exists = dao.exists(username);

      if (exists.isPresent()) {
        LOG.info("[USERNAME: {}] REALMENTE EXISTE", username);
        return true;
      }
      LOG.info("[USERNAME: {}] NAO EXISTE", username);
      return false;
    }
    LOG.info("[USERNAME: {}] NAO ESTA NA COLECAO", username);
    return false;
  }
}
