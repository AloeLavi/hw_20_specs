package models.lombok;

import lombok.Data;

@Data
public class GetSingleUserResponseLombokModel {
    private String email,first_name, last_name, avatar;
    private int id;

}
