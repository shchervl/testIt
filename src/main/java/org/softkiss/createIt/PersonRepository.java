package org.softkiss.createIt;

import org.softkiss.createIt.entities.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends MongoRepository<Person, String> {

//    List<Person> findByName(@Param("name") String name);
    Optional<Person> findById(@Param("id") String id);


}
