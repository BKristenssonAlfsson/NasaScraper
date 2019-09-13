package com.snowcatsystems.nasascraper.iotd;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
@Entity
@Table(name = "imageoftheday")
public class ImageOfTheDayEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
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

    public ImageOfTheDayEntity toEntity(ImageOfTheDayModel image) {
        ImageOfTheDayEntity iotd = new ImageOfTheDayEntity();
        iotd.setId(iotd.getId());
        iotd.setDate(image.date);
        iotd.setCopyright(image.copyright);
        iotd.setExplanation(image.explanation);
        iotd.setHdurl(image.hdurl);
        iotd.setUrl(image.url);
        iotd.setTitle(image.title);
        iotd.setMedia_type(image.media_type);
        return iotd;
    }
}
