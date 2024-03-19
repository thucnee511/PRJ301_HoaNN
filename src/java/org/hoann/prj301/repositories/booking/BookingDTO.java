package org.hoann.prj301.repositories.booking;

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
public class BookingDTO {

    private String bookingId;
    private String userId;
    private Timestamp time;
    private double total;
    private boolean status;
}
