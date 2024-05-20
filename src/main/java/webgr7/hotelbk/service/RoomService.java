package webgr7.hotelbk.service;

import org.springframework.http.ResponseEntity;
import webgr7.hotelbk.model.Room;

import java.util.List;

public interface RoomService {
    public List<Room> findRoomByHotel(Long hotelId);
}
