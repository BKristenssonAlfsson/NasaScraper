package com.snowcatsystems.nasascraper.iotd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/iotd")
public class ImageOfTheDayController {

    private ImageOfTheDayModel iotdm = new ImageOfTheDayModel();
    private ImageOfTheDayEntity iotd = new ImageOfTheDayEntity();

    @Autowired
    private ImageOfTheDayRepository imageOfTheDayRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ImageOfTheDayModel>> getAllImages() {
        try {
            List<ImageOfTheDayEntity> images = imageOfTheDayRepository.findAll();

            List<ImageOfTheDayModel> model = this.iotdm.generateModel(images);

            return new ResponseEntity<>(model, HttpStatus.OK) ;
        } catch ( Exception e ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public ResponseEntity<String> storeImage(@RequestBody ImageOfTheDayModel image) {
        ImageOfTheDayEntity temp = iotd.toEntity(image);
        System.out.println(temp.toString());
        imageOfTheDayRepository.save(temp);


        return new ResponseEntity<>("Yay", HttpStatus.OK);
    }

}
