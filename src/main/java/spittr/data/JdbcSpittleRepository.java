package spittr.data;

import org.springframework.stereotype.Repository;
import spittr.Spittle;

import java.util.List;

/**
 * @author arc3102
 * @date 2021/2/22 13:23
 */
//@Repository
public class JdbcSpittleRepository implements SpittleRepository{

    private static final String INSERT_SPITTLE = "SELECT";
    private static final String FIND_SPITTLES = "";
    private static final String FIND_SPITTLE = "SELECT * FROM";

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return null;
    }

    @Override
    public Spittle findOne(long spittleId) {
        return null;
    }

    @Override
    public Spittle saveSpittle(Spittle spittle) {
        return null;
    }

}
