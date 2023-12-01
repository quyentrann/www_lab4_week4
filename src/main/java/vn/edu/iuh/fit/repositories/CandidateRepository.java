package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.models.Candidate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CandidateRepository {
    private final JdbcTemplate jdbcTemplate;

    public CandidateRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean create(Candidate candidate) {
        String sql = "INSERT INTO candidate (can_id, phone, dob, email, full_name, address) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, candidate.getId(), candidate.getPhone(), candidate.getDob(), candidate.getEmail(), candidate.getFullName(), candidate.getAddress().getAddId()) > 0;
    }

    public boolean update(Candidate candidate) {
        String sql = "UPDATE candidate SET phone = ?, dob = ?, email = ?, full_name = ?, address = ? WHERE can_id = ?";
        return jdbcTemplate.update(sql, candidate.getPhone(), candidate.getDob(), candidate.getEmail(), candidate.getFullName(), candidate.getAddress().getAddId(), candidate.getId()) > 0;
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM candidate WHERE can_id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public Optional<Candidate> findById(long id) {
        String sql = "SELECT * FROM candidate WHERE can_id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Candidate.class), id));
    }

    public List<Candidate> findAll() {
        String sql = "SELECT * FROM candidate";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Candidate.class));
    }
}
