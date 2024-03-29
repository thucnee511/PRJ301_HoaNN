package org.hoann.prj301.repositories.user;

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
public class UserDTO {

    private String userId;
    private String name;
    private String password;
    private String phone;
    private String roleId;
    private boolean status;
}
