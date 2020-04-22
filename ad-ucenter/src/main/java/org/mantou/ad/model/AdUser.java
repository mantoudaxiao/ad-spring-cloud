package org.mantou.ad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUser {

    private String id;

    private String username;

    private String password;

    private String address;

    private String idcard;

}
