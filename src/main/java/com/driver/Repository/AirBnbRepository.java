package com.driver.Repository;

import java.util.*;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AirBnbRepository {
    HashMap<String, Hotel> hotelDB;
    HashMap<Integer, User> userDB;
    HashMap<String,Booking> bookingDB;
    HashMap<Integer,Integer> bookByAddhar;
    public AirBnbRepository() {
        hotelDB = new HashMap<>();
        userDB = new HashMap<>();
        bookingDB = new HashMap<>();
        bookByAddhar = new HashMap<>();
    }
    public String addHotel(Hotel hotel) {
        if(hotel == null || hotel.getHotelName() == null) {
            return "FAILURE";
        }
        if(hotelDB.containsKey(hotel.getHotelName())){
            return "FAILURE";
        }
        hotelDB.put(hotel.getHotelName(),hotel);
        return "SUCCESS";
    }

    public Integer addUser(User user) {
        int addharNo = user.getaadharCardNo();
        userDB.put(addharNo,user);
        return addharNo;
    }

    public String getHotelWithMostFacilities() {
        String name = "";
        int max = Integer.MIN_VALUE;
        for(String hotelName : hotelDB.keySet()) {
            Hotel hotel = hotelDB.get(hotelName);
            if(hotel.getFacilities().size() > max) {
                max = hotel.getFacilities().size();
                name = hotel.getHotelName();
            }else if(hotel.getFacilities().size() == max) {
                int lexicoGraphicallyGreater = name.compareTo(hotel.getHotelName());
                if(lexicoGraphicallyGreater > 0) {
                    name = hotel.getHotelName();
                }
            }
        }
        if(max == 0){
            return "";
        }
        return name;
    }

    public int bookARoom(Booking booking) {
        Hotel hotel = hotelDB.get(booking.getHotelName());
        if(hotel.getAvailableRooms() < booking.getNoOfRooms()){
            return -1;
        }else {

            UUID uuid = UUID.randomUUID();
            String id = String.valueOf(uuid);
            int amount = hotel.getPricePerNight() * booking.getNoOfRooms();
            booking.setBookingId(id);
            booking.setAmountToBePaid(amount);
//            Booking bk = new Booking(id,booking.getBookingAadharCard(),booking.getNoOfRooms(),booking.getBookingPersonName(),booking.getHotelName());
            bookingDB.put(id,booking);
            bookByAddhar.put(booking.getBookingAadharCard(),bookByAddhar.getOrDefault(booking.getBookingAadharCard(),0)+1);
            return amount;
        }
    }

    public int getBookings(Integer aadharCard) {
        Integer val = bookByAddhar.get(aadharCard);
        if(val != null){
            return (int)val;
        }else {
            return 0;
        }
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel = hotelDB.get(hotelName);
        List<Facility>currFaslity = hotel.getFacilities();
        for(int i = 0; i < newFacilities.size(); i++){
            if(!currFaslity.contains(newFacilities.get(i))){
                currFaslity.add(newFacilities.get(i));
            }
        }
        hotelDB.put(hotelName,hotel);
        return hotel;
    }
}
