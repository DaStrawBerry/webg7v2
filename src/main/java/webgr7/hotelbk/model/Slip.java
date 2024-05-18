package webgr7.hotelbk.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "slip")
public class Slip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date time;
    private Float price;
    private Float deposit;
    private String note;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "client_id")
    private Client c;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH})
    private List<BookedRoom> bookedRooms;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
            name = "client_voucher",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "voucher_id", referencedColumnName = "id")
    )
    private List<Voucher> vouchers;
}
