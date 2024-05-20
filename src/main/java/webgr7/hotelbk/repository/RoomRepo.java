package webgr7.hotelbk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webgr7.hotelbk.model.Room;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r JOIN r.hotel h WHERE h.location = :location")
    List<Room> findRoomsByHotelLocation(@Param("location") String location);

    Optional<Room>findById(Long id);
    List<Room> findByHotelId(Long hotelId);
}
