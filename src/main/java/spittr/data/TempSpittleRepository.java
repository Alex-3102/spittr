package spittr.data;

import spittr.Spittle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author arc3102
 * @date 2021/1/28 18:43
 */
@Component
public class TempSpittleRepository implements SpittleRepository {
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        List<Spittle> spittleList = createSpittleList(max, count);
        return spittleList;
    }

    @Override
    public Spittle findOne(long spittleId) {
        return new Spittle(spittleId, "mockMessage", new Date(), 0.0,0.0);
    }

    private List<Spittle> createSpittleList(long max, int count) {
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i=0; i < count; i++) {
            spittles.add(new Spittle(max + i - count - 1, "Spittle " + i, new Date(), 0.0, 0.0));
        }
        return spittles;
    }
}
