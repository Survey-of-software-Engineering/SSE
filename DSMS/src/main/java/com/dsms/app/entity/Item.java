package com.dsms.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document
public class Item {

    @MongoId
    String itemId;

    String itemName;

    String itemDescription;

    String itemUrl;

    Float itemPrice;

    List<Ratings> ratings;


    public Integer getOverallRatings() {

        int rating = 0;
        for(Ratings ratings : this.getRatings()) {
            rating += ratings.getRating();
        }
        return rating / this.getRatings().size();
    }
}
