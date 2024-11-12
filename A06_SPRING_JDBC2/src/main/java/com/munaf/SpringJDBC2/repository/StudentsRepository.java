package com.munaf.SpringJDBC2.repository;

import com.munaf.SpringJDBC2.demo.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentsRepository {
    private JdbcTemplate jdbc;

//    public JdbcTemplate getJdbc() {
//        return jdbc;
//    }
//
//    // this is setter injection
//    @Autowired
//    public void setJdbc(JdbcTemplate jdbc) {
//        this.jdbc = jdbc;
//    }

    StudentsRepository(JdbcTemplate jdbc) { // this is constructor injection
        this.jdbc = jdbc;
    }

    // INSERT
    public void save(Students s) {
        String query = "insert into students (rollNo,name,marks) values(?,?,?)";
        int rows = jdbc.update(query,s.getRollNo(),s.getName(),s.getMarks());
        System.out.println(rows + " rows affected");
    }

    // UPDATE
    public void updateMarks(Students s, int marks) {
        String query = "update students set marks=? where rollNo=?";
        int rows = jdbc.update(query,marks,s.getRollNo());
        System.out.println(rows + " rows updated");
    }

    // DELETE
    public void deleteRow(Students s) {
        String query = "delete from students where rollNo = ?";
        int rows = jdbc.update(query,s.getRollNo());
        System.out.println(rows + " rows deleted");
    }


    // SELECT
    public List<Students> findAll() {
        String query = "select * from students";

        RowMapper<Students> mapper = (ResultSet rs, int rowNum) -> {
            Students s = new Students();
            s.setRollNo(rs.getInt(1));
            s.setName(rs.getString(2));
            s.setMarks(rs.getInt(3));

            return s;
        };

        List<Students> list = new ArrayList<>();
        list = jdbc.query(query,mapper);
        return list;
    }
}
