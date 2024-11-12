package com.munaf.SpringJDBC.repository;

import com.munaf.SpringJDBC.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepo {
    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student s) {
        String query = "insert into student (rNo,name,age) values(?,?,?)";
        int rows = jdbc.update(query, s.getrNo(), s.getName(), s.getAge());
        System.out.println(rows + " rows affected");
    }

    public List<Student> findAll(){
        String query = "select * from student";

//        RowMapper<Student> mapper = new RowMapper<Student>() {
//            @Override
//            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Student s = new Student();
//                s.setrNo(rs.getInt(1));
//                s.setName(rs.getString(2));
//                s.setAge(rs.getInt(3));
//
//                return s;
//            }
//        };

        RowMapper<Student> mapper = (ResultSet rs, int rowNum) -> {
                Student s = new Student();
                s.setrNo(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setAge(rs.getInt(3));

                return s;
        };

        List<Student> list = new ArrayList<>();
        list = jdbc.query(query,mapper);
        return list;
    }
}
