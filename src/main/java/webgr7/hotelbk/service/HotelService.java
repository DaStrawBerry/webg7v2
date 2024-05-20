package webgr7.hotelbk.service;

import webgr7.hotelbk.model.Hotel;

import java.util.Date;
import java.util.List;

public interface HotelService {
    public List<Hotel> findHotelBy(String location, Date checkIn, Date checkOut, int pnum);

    public Hotel retriveHotelById(Long hotelId);
}
