package com.example.demo.CarwashControllers;

import com.example.demo.model.*;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;



@Controller
@SessionAttributes("userLoginID")
public class BookingController
{

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/Home/Customer/Booking")
    public  String booking(CompanyBooking companyBooking, HttpSession session, Model model) throws ExecutionException, InterruptedException {






        if (session.getAttribute("userID") == null){
            return  "redirect:/Home/Login";
        }
        else
        {

            if (companyBooking.getCompanyID() == null){

                String s = session.getAttribute("CompanyId").toString();
                companyBooking.setCompanyID(session.getAttribute("CompanyId").toString());
            }

            List<Car> cars = new ArrayList<>();
            List<CostPrice> costPrices = new ArrayList<>();
            List<Slot> slots = new ArrayList<>();
            Company company = new Company();




            company = bookingService.getCompany(companyBooking);
            slots = bookingService.getAllSlot(company.getSlot());
            costPrices = bookingService.getPrices(company.getQuotationID());
            cars = bookingService.getAllCars(session.getAttribute("userID").toString());

            session.setAttribute("CompanyId",companyBooking.getCompanyID());
            model.addAttribute("cars",cars);
            model.addAttribute("company",company);
            model.addAttribute("slots", slots);
            model.addAttribute("costPrices",costPrices);
            return "../static/Customer/booking";

        }

    }

    @RequestMapping(value = "/Home/Customer/Booking/CreateBooking")
    public  String createBooking(Booking booking, RedirectAttributes redirectAttributes, Model model, HttpSession session) throws ExecutionException, InterruptedException {



        if (session.getAttribute("userID") == null){
            return  "redirect:/Home/Login";
        }
        else
        {
            booking.setCustomerID(session.getAttribute("userID").toString());

            List<String> availableTime = new ArrayList<>();
            boolean booked;

            booked = bookingService.createBook(booking);

            if (booked){
                availableTime = bookingService.getAvailableTime(booking);
                redirectAttributes.addFlashAttribute("found",true);
                redirectAttributes.addFlashAttribute("availableTimes",availableTime);
                return  "redirect:/Home/Customer/Booking";

            }
            else {
                System.out.println("Booking was successfully created");
                return  "redirect:/Home/Customer/MyBooking";

            }

        }

    }

    @RequestMapping(value = "/Home/Customer/MyBooking")
    public  String myBooking(HttpSession session,Model model) throws ExecutionException, InterruptedException {

        if (session.getAttribute("userID") == null){
            return  "redirect:/Home/Login";
        }
        else
        {
            List<Booking> bookings = new ArrayList<>();
            String userId = session.getAttribute("userID").toString();

            bookings = bookingService.getAllMyBooking(userId);

            if (bookings != null){
                model.addAttribute("Bookings",bookings);
            }
            return  "../static/Customer/myBooking";
        }
    }

    @RequestMapping(value = "/Home/Customer/CarWash/Location")
    public String carWashLocation(HttpSession session,Location location,Model model) throws ExecutionException, InterruptedException {

        if (session.getAttribute("userID") == null){
            return  "redirect:/Home/Login";
        }
        else
        {

            if (location.getCompanyID() != null && location.getAddress() != null){

                Company company = new Company();
                company = bookingService.getCompanyDetails(location.getCompanyID());

                 String address = "https://maps.google.com/maps?q="+location.getAddress()+"&output=embed";
                 model.addAttribute("companyName",company.getCompany_Name());
                 model.addAttribute("address",address);
                 model.addAttribute("plainAddress",location.getAddress());
                return  "../static/Customer/location";
            }
            else {
                return  "redirect:/Home/Customer/Booking";
            }


        }

    }



}
