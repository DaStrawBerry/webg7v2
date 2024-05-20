package webgr7.hotelbk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.dto.RoomDTO;
import webgr7.hotelbk.model.Hotel;
import webgr7.hotelbk.model.Room;
import webgr7.hotelbk.repository.HotelRepository;
import webgr7.hotelbk.repository.RoomRepository;
import webgr7.hotelbk.service.RoomService;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    //retrieve all rooms by hotel id
    @Override
    public List<Room> getAllRoomsByHotelId(Long hotelId){
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        if(hotel != null){
            return hotel.getRooms();
        }else{
            return new ArrayList<>();
        }
    }

    //get room available by checkin, checkout and pnum
    @Override
    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate, int pnum) throws SQLException {
        return roomRepository.findAvailableRoomsByDatesAndPnum(checkInDate, checkOutDate, pnum);
    }
    @Override
    public Room createRoom(Long hotelId, RoomDTO roomDTO) throws SQLException, IOException {
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        if(hotel != null){
            List<Room> rooms = hotel.getRooms();
            Room room = new Room();
            room.setName(roomDTO.getName());
            room.setQuantity(roomDTO.getQuantity());
            room.setType(roomDTO.getType());
            room.setPnum(roomDTO.getPnum());
            room.setDes(roomDTO.getDes());
            room.setPrice(roomDTO.getPrice());
            room.setOffer(roomDTO.getOffer());
            room.setHotel(hotel);
            if(roomDTO.getFile() != null){
                byte[] photoBytes = roomDTO.getFile().getBytes();
                Blob photoBlob = new SerialBlob(photoBytes);
                room.setPhoto(photoBlob);
            }
            rooms.add(room);
            hotel.setRooms(rooms);
            return roomRepository.save(room);
        }
        else{
            throw new SQLException("Hotel not found with id: " + hotelId);
        }
    }
    @Override
    public Room getRoomById(Long roomId){
        return roomRepository.findById(roomId).orElse(null);
    }
    @Override
    public byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException {
        Room room = roomRepository.findById(roomId).orElse(null);
        if(room != null){
            Blob photoBlob = room.getPhoto();
            if(photoBlob != null){
                return photoBlob.getBytes(1, (int) photoBlob.length());
            }
        }
        return null;
    }
    @Override
    public Room updateRoomById(Long roomId, RoomDTO roomDTO) throws SQLException, IOException{
        Room room = roomRepository.findById(roomId).orElse(null);
        if(room != null){
            room.setName(roomDTO.getName());
            room.setQuantity(roomDTO.getQuantity());
            room.setType(roomDTO.getType());
            room.setPnum(roomDTO.getPnum());
            room.setDes(roomDTO.getDes());
            room.setPrice(roomDTO.getPrice());
            room.setOffer(roomDTO.getOffer());
            if(roomDTO.getFile() != null){
                byte[] photoBytes = roomDTO.getFile().getBytes();
                Blob photoBlob = new SerialBlob(photoBytes);
                room.setPhoto(photoBlob);
            }
            return roomRepository.save(room);
        }else{
            return null;
        }
    }
    @Override
    public void deleteRoom(Long roomId){
        roomRepository.deleteById(roomId);
    }
}
package webgr7.hotelbk.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.model.Room;
import webgr7.hotelbk.repository.RoomRepo;
import webgr7.hotelbk.service.RoomService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoomServiceImp implements RoomService {
    @Autowired
    private RoomRepo roomRepo;
    @Override
    public List<Room> findRoomByHotel(Long hotelId) {
        return roomRepo.findByHotelId(hotelId);
    }
}
