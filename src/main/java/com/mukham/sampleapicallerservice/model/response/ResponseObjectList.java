package com.mukham.sampleapicallerservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObjectList<T> {
    private String timestamp;
    private StatusObject status;
    // T is generic data type and all other different data types can store into this variable
    private T dataList; // you need to take care about data type when you use generic type especially getting back data from that type.
}
