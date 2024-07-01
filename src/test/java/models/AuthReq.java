package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude
public class AuthReq {

    private String userName;
    private String password;


}
