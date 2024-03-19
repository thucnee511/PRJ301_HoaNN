package org.hoann.prj301.repositories.bookingdetail;

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
public class BookingDetailDTO {

    private String bookingId;
    private String serviceId;
    private double price;
}
