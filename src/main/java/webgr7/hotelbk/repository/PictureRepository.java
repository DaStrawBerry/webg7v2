package webgr7.hotelbk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webgr7.hotelbk.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
