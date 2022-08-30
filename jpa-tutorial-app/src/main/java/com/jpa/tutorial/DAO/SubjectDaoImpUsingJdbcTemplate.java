package com.jpa.tutorial.DAO;

import com.jpa.tutorial.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SubjectDaoImpUsingJdbcTemplate implements SubjectDao {
    // we can use jdbc template for getting connection with database
    @Autowired
    JdbcTemplate template;

    //select * from subject
    @Override
    public List<Subject> findAll() {
        return template.query("select * from subject", new SubjectRowMapper());
    }

    // save subject
    public int save(Subject subject) {
        String insert = "INSERT INTO SUBJECT" +
                "(subject_name, total_marks, marks_obtained, passing_marks)" +
                " VALUES(?, ?, ?, ?)";
        return template.update(insert,
                subject.getSubjectName(),
                subject.getTotalMarks(),
                subject.getMarksObtained(),
                subject.getPassingMarks());
    }

    @Override
    public Subject findById(int id) {
        return template.queryForObject("select * from subject where subject_id = ?", new SubjectRowMapper(), id);
    }

    public int deleteSubject(int id) {
        return template.update("delete from subject where id = ?", id);
    }

    public List<Subject> findAllWithPagination(int size, int offset) {
        String query = "SELECT * FROM SUBJECT LIMIT ? OFFSET ?";
        return template.query(query, new SubjectRowMapper(), size, offset);
    }

    //using pageable
    // pageable helps in getting offset logic without writing it
    //manually everytime offset = pageSize * page
    public List<Subject> findAllWithPagination(Pageable pageable) {
        String query = "SELECT * FROM SUBJECT LIMIT ? OFFSET ?";
        return template.query(query,
                new SubjectRowMapper(),
                pageable.getPageSize(),
                pageable.getOffset());
    }


    class SubjectRowMapper implements RowMapper<Subject> {

        @Override
        public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
            Subject subject = new Subject();
            subject.setSubjectId(rs.getInt("subject_id"));
            subject.setSubjectName(rs.getString("subject_name"));
            subject.setMarksObtained(rs.getInt("marks_obtained"));
            subject.setPassingMarks(rs.getInt("passing_marks"));
            subject.setTotalMarks(rs.getInt("total_marks"));
            return subject;
        }
    }
}
