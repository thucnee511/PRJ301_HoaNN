package org.hoann.prj301.repositories.service;

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
public class ServiceDTO {

    private String serviceId;
    private String name;
    private double price;
    private boolean status;
}
