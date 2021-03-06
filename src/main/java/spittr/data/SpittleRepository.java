package spittr.data;

import spittr.Spittle;

import java.util.List;

/**
 * @author arc3102
 * @date 2021/1/26 21:30
 */
public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long spittleId);
}
