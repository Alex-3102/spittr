package spittr.data;

import spittr.Spitter;
import org.springframework.stereotype.Component;

/**
 * @author arc3102
 * @date 2021/1/28 18:41
 */
//@Component
public class TempSpitterRepository implements SpitterRepository {

    @Override
    public Spitter save(Spitter spitter) {
        return spitter;
    }

    @Override
    public Spitter findByUsername(String username) {
        Spitter spitter = new Spitter();
        spitter.setUsername(username);
        return spitter;
    }
}
