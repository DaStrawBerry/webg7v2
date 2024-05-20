package webgr7.hotelbk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.model.BookedRoom;
import webgr7.hotelbk.repository.BookedRoomRepository;
import webgr7.hotelbk.service.BookedRoomService;

import java.util.List;

@Service
public class BookedRoomServiceImp implements BookedRoomService {
    @Autowired
    BookedRoomRepository bookedRoomRepository;

    //retrieve all booked rooms
    @Override
    public List<BookedRoom> getAllBookedRooms() {
        return bookedRoomRepository.findAll();
    }
    @Override
    public BookedRoom createBookedRoom(BookedRoom bookedRoom){
        return bookedRoomRepository.save(bookedRoom);
    }
    @Override
    public BookedRoom getBookedRoomById(Long bookedRoomId){
        return bookedRoomRepository.findById(bookedRoomId).orElse(null);
    }
    @Override
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
    @Override
    public void deleteBookedRoom(Long bookedRoomId){
        bookedRoomRepository.deleteById(bookedRoomId);
    }
}
