package webgr7.hotelbk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webgr7.hotelbk.model.BookedRoom;

@Repository
public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long>{
}
