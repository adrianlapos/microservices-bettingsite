//package com.adrianlapos.apigw.authentication.repository;
//
//import com.adrianlapos.apigw.authentication.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//@Repository
//public interface UserRepository extends JpaRepository<User,Long> {
//    Optional<User> findUserByUsername(String username);
//    Optional<User> findUserByEmail(String email);
//    Optional<User> findUserById(Long id);
//    //Optional<User> findUserByUsernameOrEmail(String credential);
//    @Query("select u from User u where u.email=:credential or u.username=:credential")
//    Optional<User> findUserByCredential(@Param("credential") String credential);
//}
