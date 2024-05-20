package webgr7.hotelbk.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.model.BookedRoom;
import webgr7.hotelbk.model.Hotel;
import webgr7.hotelbk.model.Room;
import webgr7.hotelbk.repository.*;
import webgr7.hotelbk.service.HotelService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class HotelServiceImp implements HotelService {
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private BookedRoomRepo bookedRoomRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private PictureRepo pictureRepo;

    @Override
    public List<Hotel> findHotelBy(String location, Date checkIn, Date checkOut, int pnum) {
        List<Room> rooms = roomRepo.findRoomsByHotelLocation(location);

        List<Long> roomIds = new ArrayList<>();
        List<BookedRoom> bookedRooms = bookedRoomRepo.findBookedRoomsInDateRange(checkIn, checkOut);

        for(BookedRoom br : bookedRooms){
            if(roomRepo.findById(br.getRoom().getId()).isPresent()){
                Room r = roomRepo.findById(br.getRoom().getId()).get();
                if((r.getQuantity() - br.getQuantity()) * r.getPnum() < pnum){
                    roomIds.add(r.getId());
                }
            }
        }
        List<Long> hotelIds = new ArrayList<>();
        for(Room r : rooms){
            if(!roomIds.contains(r.getId()) && !hotelIds.contains(r.getHotel().getId())){
                hotelIds.add(r.getHotel().getId());
            }
        }

        List<Hotel> hotels = hotelRepo.findAllById(hotelIds);

        return hotels;
    }

    @Override
    public Hotel retriveHotelById(Long hotelId) {
        if(hotelRepo.findById(hotelId).isPresent()){
            Hotel hotel = hotelRepo.findById(hotelId).get();
            hotel.setRooms(roomRepo.findByHotelId(hotelId));
            hotel.setReviews(reviewRepo.findByHotelId(hotelId));
            hotel.setPictures(pictureRepo.findByHotelId(hotelId));
            return hotel;
        }
        return null;
    }
}
