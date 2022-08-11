package com.example.demo.CarwashControllers;

import com.example.demo.model.Car;
import com.example.demo.model.Company;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@SessionAttributes("userLoginID")
public class CustomerController
{
    @Autowired
    CustomerService customerService;

   private String userLoginID;

    @RequestMapping(value = "/Home/Customer")
    public String customerHome(HttpSession session){


        if (session.getAttribute("userID") == null){
            return  "redirect:/Home/Login";
        }
        else
        {
            userLoginID = session.getAttribute("userID").toString();
            return "../static/Customer/index";
        }

}
    @RequestMapping(value = "/Home/Customer/Profile")
    public String customerProfile(HttpSession session,Model model) throws ExecutionException, InterruptedException {


        if (session.getAttribute("userID") == null){
            return  "redirect:/Home/Login";
        }
        else
        {
            Customer customer = new Customer();
            customer = customerService.getCustomerInfo(session.getAttribute("userID").toString());

            if (customer != null){
                model.addAttribute("customerInfo",customer);
            }
            return "../static/Customer/profile";
        }


    }

    @RequestMapping(value = "/Home/Customer/Car/Save")
    public  String saveCar(HttpSession session,Car car) throws ExecutionException, InterruptedException {

        if (session.getAttribute("userID") == null){
            return  "redirect:/Home/Login";
        }
        else
        {
            car.setCustomerID(session.getAttribute("userID").toString());
            boolean  errorR = customerService.addCar(car);
            System.out.println(errorR);
            return "redirect:/Home/Customer/MyCars";
        }



    }

    @RequestMapping(value = "/Home/Customer/MyCars")
    public String myCars(HttpSession session,Car car,Model model) throws ExecutionException, InterruptedException {


        if (session.getAttribute("userID") == null){
            return  "redirect:/Home/Login";
        }
        else
        {
            List<Car> cars = new ArrayList<>();
            cars = customerService.getAllCars(session.getAttribute("userID").toString());

            if (cars.size()>0){
                model.addAttribute("cars",cars);
            }

            return "../static/Customer/cars";
        }




    }


    @RequestMapping(value = "/Home/Customer/CarUpdate/{carID}")
    public String customerCarUpdate(Model model,@PathVariable String carID){

        model.addAttribute("carID",carID);
        return "../static/Customer/car";
    }

    @RequestMapping(value = "/Home/Customer/Companies")
    public String customerCompanies(Model model,HttpSession session) throws ExecutionException, InterruptedException {

        if (session.getAttribute("userID") == null){
            return  "redirect:/Home/Login";
        }
        else
        {

            List<Company>  list =  new ArrayList<>();
            list = customerService.getAllCompanies();
            model.addAttribute("companies",list);
            return "../static/Customer/companies";

        }


    }


}
