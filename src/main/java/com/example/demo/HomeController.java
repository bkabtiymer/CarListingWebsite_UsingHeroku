package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    CatagoryRepository catagoryRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listCatagory(Model model) {
        model.addAttribute("catagories", catagoryRepository.findAll());
        return "catagorylist";
    }

    @GetMapping("/addcatagory")
    public String catagoryForm(Model model) {
        model.addAttribute("car", carRepository.findAll());
        model.addAttribute("catagory", new Catagory());
        return "catagoryform";
    }

    @PostMapping("/processcatagory")
    public String processForm(@Valid Catagory catagory, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("cars", carRepository.findAll());
            return "catagoryform";
        }
        catagoryRepository.save(catagory);
        return "redirect:/addcars";
    }

    @RequestMapping("/detail/{id}")

    public String showCatagory(@PathVariable("id") long id, Model model) {
        model.addAttribute("catagory", catagoryRepository.findById(id).get());

        return "carlist";
    }

    @RequestMapping("/update/{id}")
    public String updateCatagory(@PathVariable("id") long id, Model model) {
        model.addAttribute("cars", carRepository.findAll());

        model.addAttribute("catagory", catagoryRepository.findById(id).get());
        return "catagoryform";

    }

    @RequestMapping("/delete/{id}")
    public String delCatagory(@PathVariable("id") long id) {
        catagoryRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/addcar/{id}")
    public String addCars(@PathVariable("id") long id, Model model) {
        model.addAttribute("catagory", catagoryRepository.findById(id).get());

        return "redirect:/addcars";
    }


    @GetMapping("/addcars")
    public String carForm(Model model) {
        model.addAttribute("catagories", catagoryRepository.findAll());
        model.addAttribute("car", new Car());

        return "carform";
    }

    @PostMapping("/processcar")
    public String processForm(@ModelAttribute Car car,
                              @RequestParam ("file")MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/processcar";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            car.setImage(uploadResult.get("url").toString());
            carRepository.save(car);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/processcar";
        }
            return "redirect:/";
        }


    @RequestMapping("/detail/car/{id}")
    public String showcar(@PathVariable("id") long id, Model model){
        model.addAttribute("catagories", catagoryRepository.findAll());
        model.addAttribute("car", carRepository.findById(id).get());

        return "carlist";
    }
    @RequestMapping("/update/car{id}")
    public String updatecar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "carform";

    }
    @RequestMapping("/delete/car{id}")
    public  String delcar(@PathVariable("id") long id){
        carRepository.deleteById(id);
        return "redirect:/";
    }
}
