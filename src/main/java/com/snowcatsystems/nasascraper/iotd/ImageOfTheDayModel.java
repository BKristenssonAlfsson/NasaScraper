package com.snowcatsystems.nasascraper.iotd;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ToString
@Setter
@Getter
@NoArgsConstructor
public class ImageOfTheDayModel {

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

    public ImageOfTheDayModel toModel(ImageOfTheDayEntity image) {

        ImageOfTheDayModel iotdm = new ImageOfTheDayModel();
        iotdm.hdurl = image.hdurl;
        iotdm.explanation = image.explanation;
        iotdm.date = image.date;
        iotdm.title = image.title;
        iotdm.copyright = image.copyright;

        return iotdm;
    }

    public List<ImageOfTheDayModel> generateModel(List<ImageOfTheDayEntity> d) {

        List<ImageOfTheDayModel> models = new ArrayList<>();

        for ( ImageOfTheDayEntity e : d) {
            ImageOfTheDayModel iotdm = new ImageOfTheDayModel();
            iotdm.hdurl = e.hdurl;
            iotdm.explanation = e.explanation;
            iotdm.date = e.date;
            iotdm.title = e.title;
            iotdm.copyright = e.copyright;
            iotdm.url = e.url;
            iotdm.media_type = e.media_type;
            models.add(iotdm);
        }
        return models;
    }
}
