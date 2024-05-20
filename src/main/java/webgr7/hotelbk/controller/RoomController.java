package webgr7.hotelbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webgr7.hotelbk.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/of-hotel/{hotel_id}")
    public ResponseEntity<?> getHotelRooms(@PathVariable Long hotel_id){
        return ResponseEntity.ok(roomService.findRoomByHotel(hotel_id));
    }
}
