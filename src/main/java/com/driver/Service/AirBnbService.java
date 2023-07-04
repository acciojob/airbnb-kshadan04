package com.driver.Service;

import com.driver.Repository.AirBnbRepository;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AirBnbService {

    @Autowired
    AirBnbRepository airBnbRepository = new AirBnbRepository();

    public String addHotel(Hotel hotel) {
        return airBnbRepository.addHotel(hotel);
    }

    public Integer addUser(User user) {
        return airBnbRepository.addUser(user);
    }

    public String getHotelWithMostFacilities() {
        return airBnbRepository.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        return airBnbRepository.bookARoom(booking);
    }

    public int getBookings(Integer aadharCard) {
        return airBnbRepository.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return airBnbRepository.updateFacilities(newFacilities,hotelName);
    }
}
