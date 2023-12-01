package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.models.Company;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CompanyRepository {
    private final JdbcTemplate jdbcTemplate;

    public CompanyRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean create(Company company) {
        String sql = "INSERT INTO company (com_id, name, about, address, email, phone, web_url) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, company.getId(), company.getName(), company.getAbout(), company.getAddress().getAddId(), company.getEmail(), company.getPhone(), company.getWebUrl()) > 0;
    }

    public boolean update(Company company) {
        String sql = "UPDATE company SET name = ?, about = ?, address = ?, email = ?, phone = ?, web_url = ? WHERE com_id = ?";
        return jdbcTemplate.update(sql, company.getName(), company.getAbout(), company.getAddress().getAddId(), company.getEmail(), company.getPhone(), company.getWebUrl(), company.getId()) > 0;
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM company WHERE com_id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public Optional<Company> findById(long id) {
        String sql = "SELECT * FROM company WHERE com_id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Company.class), id));
    }

    public List<Company> findAll() {
        String sql = "SELECT * FROM company";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Company.class));
    }
}
