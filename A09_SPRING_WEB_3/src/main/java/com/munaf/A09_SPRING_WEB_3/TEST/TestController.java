package com.munaf.A09_SPRING_WEB_3.TEST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @Autowired
    private TestRepository testRepository;

    @PostMapping("/createTest")
    public TestDTO createTest(@RequestBody TestDTO testDTO) {

        TestEntity testEntity = testDTO.mapToEntity();

        TestEntity savedTestEntity = testRepository.save(testEntity);

        return TestDTO.mapToDto(testEntity);

    }

}
