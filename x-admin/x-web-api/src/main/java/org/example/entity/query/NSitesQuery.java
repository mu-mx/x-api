package org.example.entity.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class NSitesQuery implements Serializable {

    private String title;

    private Integer state;

    private Integer pId;

}
