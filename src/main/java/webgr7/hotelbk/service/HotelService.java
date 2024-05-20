package webgr7.hotelbk.service;

import webgr7.hotelbk.dto.HotelDTO;
import webgr7.hotelbk.model.Hotel;
import webgr7.hotelbk.model.Picture;

import java.sql.Blob;
import java.util.List;

public interface HotelService {
    public List<Hotel> getAllHotels();

    //create Hotel by passing HotelDTO object
    public Hotel createHotel(HotelDTO hotelDTO);


    public Hotel getHotelById(Long hotelId);

    public void updatePictureHotel(Long hotelId, Blob photoBlob) throws Exception;

    public Hotel updateHotelById(Long hotelId, HotelDTO hotelDTO);

    public void deleteHotel(Long hotelId);

}
