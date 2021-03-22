package spittr.web;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.Spitter;
import spittr.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

/**
 * @author arc3102
 * @date 2021/1/28 18:14
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository){
        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new Spitter());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture,
                                      @Valid Spitter spitter, Errors errors,
                                      RedirectAttributes model) throws IOException {
        System.out.println(errors.hasErrors());
        if (errors.hasErrors()) {
            return "registerForm";
        }
        spitterRepository.save(spitter);
        if (!profilePicture.isEmpty()) {
            profilePicture.transferTo(new File("/" + profilePicture.getOriginalFilename()));
        }
        System.out.println(spitter);
        model.addAttribute("username", spitter.getUsername());
        model.addFlashAttribute("spitter", spitter);
        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String username, Model model){
//        if (!model.containsAttribute("spitter")) {
//            System.out.println("model里没有spitter对象，重新查找");
        System.out.println(model);
        model.addAttribute(spitterRepository.findByUsername(username));
        System.out.println(model);
        System.out.println(spitterRepository.findByUsername(username));
//        } else {
//            System.out.println("model里存在spittr对象，不需要重新查找");
//        }
        return "profile";
    }

}