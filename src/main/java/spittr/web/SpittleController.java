package spittr.web;

import spittr.Spittle;
import spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spittr.exception.SpittleNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author arc3102
 * @date 2021/1/26 21:40
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";
    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }


    //  参数查询
    @RequestMapping(method = RequestMethod.GET)
    public String spittles(@RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max, @RequestParam(value="count", defaultValue="20") int count, Model model){
        model.addAttribute(spittleRepository.findSpittles(max, count));
//        model.addAttribute(createSpittleList(20));
        return "spittles";
    }

    //  路径参数
    @RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
    public String spittle(
            @PathVariable("spittleId") long spittleId,
            Model model) {
        Spittle spittle = spittleRepository.findOne(spittleId);
        if (spittle == null) {
            throw new SpittleNotFoundException();
        }
        model.addAttribute(spittle);
        return "spittle";
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public String saveSpittle(SpittleForm form, Model model) {
//        spittleRepository.save(new Spittle(null, form.getMessage(), new Date(), form.getLongitude(), form.getLatitude()));
//        return "redirect:/spittles";
//    }
}
