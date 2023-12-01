package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.models.Experience;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ExperienceRepository {
    private final JdbcTemplate jdbcTemplate;

    public ExperienceRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean create(Experience experience) {
        String sql = "INSERT INTO experience (exp_id, toDate, can_id, from_date, company, role, work_desc) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, experience.getId(), experience.getToDate(), experience.getCandidate().getId(), experience.getFromDate(), experience.getCompanyName(), experience.getRole(), experience.getWorkDescription()) > 0;
    }

    public boolean update(Experience experience) {
        String sql = "UPDATE experience SET toDate = ?, can_id = ?, fromDate = ?, company = ?, role = ?, work_desc = ? WHERE exp_id = ?";
        return jdbcTemplate.update(sql, experience.getToDate(), experience.getCandidate().getId(), experience.getFromDate(), experience.getCompanyName(), experience.getRole(), experience.getWorkDescription(), experience.getId()) > 0;
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM experience WHERE exp_id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public Optional<Experience> findById(long id) {
        String sql = "SELECT * FROM experience WHERE exp_id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Experience.class), id));
    }

    public List<Experience> findAll() {
        String sql = "SELECT * FROM experience";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Experience.class));
    }
}
