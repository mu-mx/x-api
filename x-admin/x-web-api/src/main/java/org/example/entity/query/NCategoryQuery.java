package org.example.entity.query;

import java.io.Serializable;
import lombok.Data;

@Data
public class NCategoryQuery implements Serializable {

    private String title;

    private String categoryId;

}
