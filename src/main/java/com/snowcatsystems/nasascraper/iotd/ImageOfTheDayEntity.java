package com.snowcatsystems.nasascraper.iotd;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "imageoftheday")
public class ImageOfTheDayEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String hdurl;
    public String title;
    public String url;
    public String media_type;
    public Date date;
    @Lob
    @Column( name="explanation", length = 512)
    public String explanation;
    public String copyright;

}
