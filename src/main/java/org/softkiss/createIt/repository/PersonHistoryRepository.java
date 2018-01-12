package org.softkiss.createIt.repository;

import org.softkiss.createIt.entities.PersonHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "personHistory", path = "personHistory")
public interface PersonHistoryRepository extends MongoRepository<PersonHistory, String> {
}
