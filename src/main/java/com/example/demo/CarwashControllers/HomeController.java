package com.example.demo.CarwashControllers;


import com.example.demo.model.Customer;
import com.example.demo.model.Login;
import com.example.demo.service.HomeService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.concurrent.ExecutionException;

@Controller
public class HomeController
{


    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "/Home/About")
    public  String About(){
        return "About";
    }

    @RequestMapping(value = "/Home")
    public String home()
    {
        return "index";
    }

    @RequestMapping(value = "/Home/Login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/Home/Login/Customer")
    public String customerLogin(RedirectAttributes redirectAttributes,Model model, Login login,HttpSession session) throws ExecutionException, InterruptedException, FirebaseAuthException {

        Customer customer = new Customer();
        customer = homeService.customerLogin(login);

        System.out.println(customer);

        if (customer == null){
            redirectAttributes.addFlashAttribute("errorFound",true);
            redirectAttributes.addFlashAttribute("errorMsg","No Customer Found");
            return  "redirect:/Home/Login";
        }
        else if (login.getPassword().equals(customer.getPassword())){
            redirectAttributes.addFlashAttribute("msg","Welcome "+customer.getName()+" "+customer.getSurname());

            session.setAttribute("userID",customer.getCustomerID());
            return  "redirect:/Home/Customer";
        }
        else {
            redirectAttributes.addFlashAttribute("errorFound",true);
            redirectAttributes.addFlashAttribute("errorMsg","Password is incorrect");
            return  "redirect:/Home/Login";
        }

    }

    @RequestMapping(value = "/Home/Customer/Logout")
    public  String logout(HttpSession session)
    {
        session.invalidate();
        return  "redirect:/Home/Login";
    }
    @RequestMapping(value = "/Home/Register/Create")
    public String registerPage(RedirectAttributes redirectAttributes, Customer customer) throws FirebaseAuthException, ExecutionException, InterruptedException {
        boolean exist;
         exist = homeService.createCustomer(customer);

         if (exist){
             System.out.println("exist");
             redirectAttributes.addFlashAttribute("add","Customer already exist");
             redirectAttributes.addFlashAttribute("exist",true);

             return "redirect:/Home/Register";
         }
         else {
             return "redirect:/Home/Login";
         }

    }

    @RequestMapping(value = "/Home/Register")
    public String register(){
        return "register";
    }


}
