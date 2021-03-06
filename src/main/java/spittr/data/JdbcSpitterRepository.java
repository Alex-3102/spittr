package spittr.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spittr.Spitter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author arc3102
 * @date 2021/2/15 15:07
 */
//@Repository
public class JdbcSpitterRepository implements SpitterRepository{

    private JdbcOperations jdbcOperations;

    private static final String INSERT_SPITTER = "INSERT INTO spitter (username, password, firstname, lastname, email) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_SPITTER = "SELECT * FROM spitter WHERE username=?";

    @Autowired
    public JdbcSpitterRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations= jdbcOperations;
    }

    @Override
    public Spitter save(Spitter spitter) {
        int cnt = jdbcOperations.update(INSERT_SPITTER,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFirstName(),
                spitter.getLastName(),
                spitter.getEmail());
        if(cnt == 1){
            return findByUsername(spitter.getUsername());
        }
        return null;
    }

    @Override
    public Spitter findByUsername(String username) {
        return jdbcOperations.queryForObject(
                SELECT_SPITTER, new SpitterRowMapper(), username
        );
    }

    private static final class SpitterRowMapper implements RowMapper<Spitter> {
        public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spitter(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("email"));
        }
    }
}
