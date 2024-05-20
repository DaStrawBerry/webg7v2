package webgr7.hotelbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webgr7.hotelbk.service.HotelService;

import java.util.Date;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping("/search")
    private ResponseEntity<?> searchHotel(
            @RequestParam("lcation") String location,
            @RequestParam("checkIn") @DateTimeFormat(pattern = "dd-MM-yyyy") Date checkIn,
            @RequestParam("checkOut") @DateTimeFormat(pattern = "dd-MM-yyyy") Date checkOut,
            @RequestParam("pnum") int pnum){
        return ResponseEntity.ok(hotelService.findHotelBy(location, checkIn, checkOut, pnum));
    }

    @GetMapping("/detail/{hotel_id}")
    private ResponseEntity<?> getHotel(@PathVariable Long hotel_id){
        return ResponseEntity.ok(hotelService.retriveHotelById(hotel_id));
    }
}
