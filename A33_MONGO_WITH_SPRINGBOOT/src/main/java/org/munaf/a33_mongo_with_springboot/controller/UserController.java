package org.munaf.a33_mongo_with_springboot.controller;

import org.munaf.a33_mongo_with_springboot.entity.LearningCourse;
import org.munaf.a33_mongo_with_springboot.entity.UserMaster;
import org.munaf.a33_mongo_with_springboot.entity.UserRole;
import org.munaf.a33_mongo_with_springboot.repository.LearningCourseRepository;
import org.munaf.a33_mongo_with_springboot.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final LearningCourseRepository learningCourseRepository;
    private final MongoTemplate mongoTemplate;

    public UserController(UserRepository userRepository, LearningCourseRepository learningCourseRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.learningCourseRepository = learningCourseRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping()
    public Object createUser(@RequestBody UserMaster userMaster) {
        if (userMaster.getAddress() == null) {
            // if no address then you can throw error or create dummy address
        }

        // create course for user
        LearningCourse mongoCourse = LearningCourse.builder()
                .name("MongoDB")
                .description("MongoDB for beginners")
                .tags(List.of("MongoDB", "NoSQL", "Database"))
                .price(new BigDecimal(100))
                .build();

        // save
        mongoCourse = learningCourseRepository.save(mongoCourse);


        LearningCourse javaCourse = LearningCourse.builder()
                .name("Java")
                .description("Java for beginners")
                .tags(List.of("Java", "Programming", "Language"))
                .price(new BigDecimal(100))
                .build();

        // save
        javaCourse = learningCourseRepository.save(javaCourse);

        // set courses to user
        userMaster.setEnrolledCourses(List.of(mongoCourse, javaCourse));

        if (userRepository.existsByEmail(userMaster.getEmail())) {
            return "User already exists with email: " + userMaster.getEmail();
        }
        return userRepository.save(userMaster);
    }

    @GetMapping("/find-by-id/{id}")
    public Object getUserById(@PathVariable String id) {
        UserMaster userMaster =  userRepository.findById(id).orElse(null); // in JPA we used to do : findByAddress_City(city);
        return userMaster;
    }

    @GetMapping("/get-user-by-city/{city}")
    public List<UserMaster> getUserByCity(@PathVariable String city) {
        return userRepository.findByAddressCity(city); // in JPA we used to do : findByAddress_City(city);
    }

    @GetMapping("/get-user-by-city-and-country/{city}/{country}")
    public List<UserMaster> getUserByCityAndState(@PathVariable String city, @PathVariable String country) {
        return userRepository.findByAddressCityAndAddressState(city, country); // in JPA we used to do : findByAddress_City(city);
    }


    @GetMapping("/get-user-with-critaria")
    public List<UserMaster> getUserWithCriteria() {

//        List<UserMaster> users = mongoTemplate.findAll(UserMaster.class);

        String name = "Munaf1212";
        List<UserRole> userRoles = List.of(UserRole.USER, UserRole.TESTER);

        Query query = new Query(
                Criteria.where("name").is(name)
                        .and("userRoles").in(userRoles)
        );

        // we can also do projection for optimization
        query.fields().include("name").include("email"); // this will only fetch name and email from db
        // else everything will be none

        // we can do pagination
        // query.limit(10).skip(0); // 0 - 9
        // query.with(PageRequest.of(0, 10)); // 0 - 9

        List<UserMaster> users = mongoTemplate.find(query, UserMaster.class, "users");

        return users;

    }

}
