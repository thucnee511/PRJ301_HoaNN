package org.hoann.prj301.repositories.feedback;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FeedbackDTO {

    private String feedbackId;
    private String bookingId;
    private String description;
    private Timestamp time;
    private String reply;
    private boolean status;
}
