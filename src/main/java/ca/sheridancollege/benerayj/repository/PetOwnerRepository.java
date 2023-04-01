package ca.sheridancollege.benerayj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.benerayj.bean.PetOwner;

@Repository
public interface PetOwnerRepository extends MongoRepository<PetOwner, Long> {

}
