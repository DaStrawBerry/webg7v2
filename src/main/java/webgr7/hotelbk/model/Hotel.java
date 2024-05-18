package webgr7.hotelbk.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.List;

@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String star;
    private String location;
    private String type;
    private String status;
    private String tel;
    private String contact;
    private String des;

    @OneToMany(mappedBy="hotel",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Room> rooms;

    @OneToMany(mappedBy="hotel",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy="hotel",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Picture> pictures;
}
