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
