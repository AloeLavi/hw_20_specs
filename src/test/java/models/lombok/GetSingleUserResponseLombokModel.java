package models.lombok;

import lombok.Data;

import java.util.List;

@Data
public class GetSingleUserResponseLombokModel {


    public List<ResponseData> data;
    public List<Support> support;


}
