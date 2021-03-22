package spittr.web;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author arc3102
 * @date 2021/2/22 17:39
 */
@Controller
@RequestMapping({"/spitter/delete"})
public class RemoveRedisCacheValueController {

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @CacheEvict(value = "redis")
    public String remove(@PathVariable String username){
        return "redirect:/";
    }
}
