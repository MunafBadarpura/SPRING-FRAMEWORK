package com.munaf.A09_SPRING_WEB_3.TEST;

import lombok.Data;

@Data
public class TestDTO {

    private long id;
    private long mID; // Change mID to mId

    public TestEntity mapToEntity() {
        TestEntity testEntity = new TestEntity();

        testEntity.setId(this.id);
        testEntity.setMakerId(this.mID); // Change mID to mId
        return testEntity;
    }

    public static TestDTO mapToDto(TestEntity testEntity) {
        TestDTO testDTO = new TestDTO();

        testDTO.setId(testEntity.getId());
        testDTO.setmID(testEntity.getMakerId()); // Change mID to mId

        return testDTO;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getmID() {
        return mID;
    }

    public void setmID(long mID) {
        this.mID = mID;
    }
}