package spittr.web.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spittr.Spittle;
import spittr.data.SpittleRepository;
import spittr.exception.SpittleNotFoundException;

/**
 * @author arc3102
 * @date 2021/3/1 22:48
 */
@Controller
@RequestMapping("/spittle")
public class ShowSpittlesController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";
    private SpittleRepository spittleRepository;

    @Autowired
    public ShowSpittlesController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }


    //  参数查询
    @RequestMapping(method = RequestMethod.GET)
    public String spittles(@RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max, @RequestParam(value="count", defaultValue="20") int count, Model model){
        model.addAttribute(spittleRepository.findSpittles(max, count));
//        model.addAttribute(createSpittleList(20));
        model.addAttribute(new Spittle());
        return "spittles";
    }

    //  路径参数
    @RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
    public String spittle(
            @PathVariable("spittleId") long spittleId,
            Model model) {
        Spittle spittle = spittleRepository.findOne(spittleId);
        if (spittle == null) {
            throw new SpittleNotFoundException(spittleId);
        }
        model.addAttribute(spittle);
        return "spittle";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveSpittle(Spittle spittle, Model model) {
        spittleRepository.saveSpittle(spittle);
        return "redirect:/spittles";
    }
}
