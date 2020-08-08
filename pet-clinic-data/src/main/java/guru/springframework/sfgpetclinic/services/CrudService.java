package guru.springframework.sfgpetclinic.services;

import java.util.Set;

public interface CrudService<T, S> {

  Set<T> findAll();

  T findById(S id);

  T save(T object);

  void delete(T object);

  void deleteById(S id);

}
