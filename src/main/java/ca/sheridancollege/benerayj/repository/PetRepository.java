package ca.sheridancollege.benerayj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.benerayj.bean.Pet;

@Repository
public interface PetRepository extends MongoRepository<Pet, Long> {

}
