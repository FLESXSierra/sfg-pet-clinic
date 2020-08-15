package guru.springframework.sfgpetclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.BaseEntity;

public abstract class AbstractMapService<T extends BaseEntity, K extends Long> {

  protected Map<Long, T> map = new HashMap<>();

  Set<T> findAll() {
    return new HashSet<>(map.values());
  }

  T findById(K id) {
    return map.get(id);
  }

  T save(T object) {
    if (object != null) {
      if (object.getId() == null) {
        object.setId(getNextId());
      }
      map.put(object.getId(), object);
      return object;
    }
    throw new IllegalArgumentException("Object cannot be null");
  }

  void deleteById(K id) {
    map.remove(id);
  }

  void delete(T object) {
    map.entrySet()
        .removeIf(
            entry -> entry.getValue()
                .equals(object));
  }

  private Long getNextId() {
    Long nextId;
    try {
      nextId = Collections.max(map.keySet()) + 1L;
    }
    catch (NoSuchElementException e) {
      nextId = 1L;
    }
    return nextId;
  }

}
