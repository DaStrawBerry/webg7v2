package webgr7.hotelbk.dto;

import lombok.Data;
import webgr7.hotelbk.model.Client;

import java.util.Date;
@Data
public class BillDTO {
    private Float amount;
    private String type;
    private Date time;
    private Long clientID;
    private Long slipID;
}
