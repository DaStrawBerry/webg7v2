package webgr7.hotelbk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.model.BookedRoom;
import webgr7.hotelbk.repository.BookedRoomRepository;
import java.util.List;

@Service
public class BookedRoomService {
    @Autowired
    BookedRoomRepository bookedRoomRepository;

    //retrieve all booked rooms
    public List<BookedRoom> getAllBookedRooms() {
        return bookedRoomRepository.findAll();
    }

    public BookedRoom createBookedRoom(BookedRoom bookedRoom){
        return bookedRoomRepository.save(bookedRoom);
    }

    public BookedRoom getBookedRoomById(Long bookedRoomId){
        return bookedRoomRepository.findById(bookedRoomId).orElse(null);
    }

    public boolean updateBookedRoomById(Long bookedRoomId, BookedRoom bookedRoom){
        BookedRoom foundBookedRoom = bookedRoomRepository.findById(bookedRoomId).orElse(null);
        if(foundBookedRoom != null){
            foundBookedRoom.setCheckIn(bookedRoom.getCheckIn());
            foundBookedRoom.setCheckOut(bookedRoom.getCheckOut());
            foundBookedRoom.setPrice(bookedRoom.getPrice());
            bookedRoomRepository.save(foundBookedRoom);
            return true;
        }else{
            return false;
        }
    }

    public void deleteBookedRoom(Long bookedRoomId){
        bookedRoomRepository.deleteById(bookedRoomId);
    }
}
