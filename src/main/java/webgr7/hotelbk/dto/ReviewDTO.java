package webgr7.hotelbk.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long client_id;
    private Long bill_id;
    private Float rate;
    private String comment;
}
