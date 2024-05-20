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
package webgr7.hotelbk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.dto.HotelDTO;
import webgr7.hotelbk.model.Hotel;
import webgr7.hotelbk.model.Picture;
import webgr7.hotelbk.repository.HotelRepository;
import webgr7.hotelbk.repository.PictureRepository;
import webgr7.hotelbk.repository.RoomRepository;
import webgr7.hotelbk.service.HotelService;

import java.sql.Blob;
import java.util.List;

@Service
public class HotelServiceImp implements HotelService {
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    PictureRepository pictureRepository;
    //retrieve all hotels
    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    //create Hotel by passing HotelDTO object
    @Override
    public Hotel createHotel(HotelDTO hotelDTO){
        Hotel newHotel = new Hotel();
        newHotel.setName(hotelDTO.getName());
        newHotel.setStar(hotelDTO.getStar());
        newHotel.setLocation(hotelDTO.getLocation());
        newHotel.setType(hotelDTO.getType());
        newHotel.setStatus(hotelDTO.getStatus());
        newHotel.setTel(hotelDTO.getTel());
        newHotel.setContact(hotelDTO.getContact());
        newHotel.setDes(hotelDTO.getDes());
        return hotelRepository.save(newHotel);
    }

    @Override
    public Hotel getHotelById(Long hotelId){
        return hotelRepository.findById(hotelId).orElse(null);
    }

    @Override
    public void updatePictureHotel(Long hotelId, Blob photoBlob) throws Exception {
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        if(hotel != null){
            Picture picture = new Picture();
            picture.setHotel(hotel);
            picture.setData(photoBlob);
            pictureRepository.save(picture);

            hotel.getPictures().add(picture);
            hotelRepository.save(hotel);
        }else{
            throw new Exception("Hotel not found");
        }
    }

    @Override
    public Hotel updateHotelById(Long hotelId, HotelDTO hotelDTO){
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        if(hotel != null){
            hotel.setName(hotelDTO.getName());
            hotel.setStar(hotelDTO.getStar());
            hotel.setLocation(hotelDTO.getLocation());
            hotel.setType(hotelDTO.getType());
            hotel.setStatus(hotelDTO.getStatus());
            hotel.setTel(hotelDTO.getTel());
            hotel.setContact(hotelDTO.getContact());
            hotel.setDes(hotelDTO.getDes());
            return hotel;
        }else{
            return null;
        }
    }

    @Override
    public void deleteHotel(Long hotelId){
        hotelRepository.deleteById(hotelId);
    }
}
