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
