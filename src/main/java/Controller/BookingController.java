package Controller;

import ch.qos.logback.core.model.Model;
import entity.Booking;
import entity.BookingService;
import entity.CustomerService;
import entity.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addText("bookings");
        return "bookings";
    }

    @GetMapping("/create")
    public String createBookingForm(Model model) {
        model.addText("booking");
        model.addText("houses");
        model.addText("customers");
        return "create-booking";
    }

    @PostMapping
    public String saveBooking(@ModelAttribute Booking booking) {
        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/{id}")
    public String getBookingById(@PathVariable("id") Long id, Model model) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        if (booking.isPresent()) {
            model.addText("booking");
            return "booking";
        } else {
            return "redirect:/bookings";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }
}