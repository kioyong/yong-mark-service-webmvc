package com.yong.mark.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "table")
public class Table {

    @Id
    private String id;
    private String status;
    private String tableId;
    private Double minBet;
    private Date date;
}
