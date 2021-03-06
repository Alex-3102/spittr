package spittr.data;

import spittr.Spitter;

/**
 * @author arc3102
 * @date 2021/1/28 18:31
 */
public interface SpitterRepository {
    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);
}
