package UserDetails.UserDetails.repository;

import UserDetails.UserDetails.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends MongoRepository<User,String> {
    boolean existsById(String id);
    public Optional<User> findByuserId(String productName);
    public Optional<User> findByUserEmail(String userEmail);
}
