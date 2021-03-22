package spittr.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spittr.Spittle;
import spittr.data.SpittleRepository;
import spittr.exception.SpittleNotFoundException;

import java.net.URI;
import java.util.List;

/**
 * @author arc3102
 * @date 2021/3/1 13:43
 */
//@RestController
@RequestMapping("/spittles")
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository){
        this.spittleRepository = spittleRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Spittle> spittles(@RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max, @RequestParam(value="count", defaultValue="20") int count, Model model){
        return spittleRepository.findSpittles(max, count);
    }

    /*//初级：使用201提示
    @RequestMapping(method = RequestMethod.POST, consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Spittle saveSpittle(@RequestBody Spittle spittle) {
        return spittleRepository.saveSpittle(spittle);
    }*/

    /*//使用ResponseEntity设置响应的头部信息
    @RequestMapping(method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Spittle> saveSpittle(@RequestBody Spittle spittle) {
        Spittle spittle1 = spittleRepository.saveSpittle(spittle);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = URI.create("http://localhost:8088/spittles/" + spittle1.getId());
        headers.setLocation(locationUri);
        ResponseEntity<Spittle> responseEntity = new ResponseEntity<Spittle>(spittle1, headers, HttpStatus.CREATED);
        return responseEntity;
    }*/

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Spittle> saveSpittle(@RequestBody Spittle spittle, UriComponentsBuilder ucb) {
        Spittle spittle1 = spittleRepository.saveSpittle(spittle);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/spittles/")
                                .path(String.valueOf(spittle.getId()))
                                .build()
                                .toUri();
        headers.setLocation(locationUri);
        ResponseEntity<Spittle> responseEntity = new ResponseEntity<Spittle>(spittle, headers, HttpStatus.CREATED);
        return responseEntity;
    }
    //Rest初级用法
    /*//不带错误信息
    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public Spittle spittleById(@PathVariable long id) {
        System.out.println(spittleRepository.findOne(id));
        return spittleRepository.findOne(id);
    }*/

    /*//使用ResponseEntity返回404错误信息，但请求体为空
    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<Spittle> spittleById(@PathVariable long id) {
        Spittle spittle = spittleRepository.findOne(id);
        HttpStatus status = spittle != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Spittle>(spittle, status);
    }*/

    /*//创建Error类并使用ResponseEntity返回错误信息
    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> spittleById(@PathVariable long id) {
        Spittle spittle = spittleRepository.findOne(id);
        if (spittle == null) {
            spittr.dto.Error error = new spittr.dto.Error(4, "Spittle [" + id + "] not found");
            return new ResponseEntity<spittr.dto.Error>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Spittle>(spittle, HttpStatus.OK);
    }*/

    //使用错误处理器
    //1.定义处理器方法
    @ExceptionHandler(SpittleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public spittr.dto.Error spittleNotFound(SpittleNotFoundException e) {
        long spittleId = e.getSpittleId();
        return new spittr.dto.Error(4, "Spittle [" + spittleId + "] not found");
    }
    //2.重写控制器方法
    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<Spittle> spittleById(@PathVariable long id) {
        Spittle spittle = spittleRepository.findOne(id);
        if (spittle == null) {
            throw new SpittleNotFoundException(id);
        }
        return new ResponseEntity<Spittle>(spittle, HttpStatus.OK);
    }

}
