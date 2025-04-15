package com.example.back.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseEntity {
    @JsonProperty("id")
    private int id;
}
