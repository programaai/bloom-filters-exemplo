package ai.programa.bloomfilters;

import ai.programa.bloomfilters.data.UserRepository;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserResource {
  private final UserRepository repository;

  @Autowired
  public UserResource(UserRepository repository) {
    this.repository = repository;
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<?> create(@RequestBody String username) {
    final long userId = repository.create(username);
    return ResponseEntity.ok(Long.toString(userId));
  }

  @RequestMapping(value = "/{username}", method = RequestMethod.GET)
  public ResponseEntity<?> findUser(@PathVariable("username") String username) {
    if (repository.exists(username)) {
      return ResponseEntity.ok()
          .build();
    }
    return ResponseEntity.notFound().build();
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public String getUsers() {
    return String.join("\n", repository.all());
  }
}
