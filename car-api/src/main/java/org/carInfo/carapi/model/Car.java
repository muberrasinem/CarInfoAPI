package org.muberra.carapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "Car")
@EqualsAndHashCode
public class Car implements Serializable {

    @Id
    @Indexed //for faster retrieval
    private Long id;
    private String make;
    private String model;
    private int year;
    private String color;

}
