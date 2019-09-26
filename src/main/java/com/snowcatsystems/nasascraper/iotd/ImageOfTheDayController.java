package com.snowcatsystems.nasascraper.iotd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iotd")
@CrossOrigin("http://127.0.0.1:3000")
public class ImageOfTheDayController {

    private ImageOfTheDayModel iotdm = new ImageOfTheDayModel();
    private ImageOfTheDayEntity iotd = new ImageOfTheDayEntity();

    @Autowired
    private ImageOfTheDayService imageOfTheDayService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ImageOfTheDayModel>> getAllImages() {
        try {
            List<ImageOfTheDayEntity> images = imageOfTheDayService.findAll();

            List<ImageOfTheDayModel> model = this.iotdm.generateModel(images);

            return new ResponseEntity<>(model, HttpStatus.OK) ;
        } catch ( Exception e ) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public ResponseEntity<String> storeImage(@RequestBody ImageOfTheDayModel image) {
        ImageOfTheDayEntity temp = iotd.toEntity(image);

        try {

            ImageOfTheDayEntity check = imageOfTheDayService.findByTitle(temp.title);
            if (check.title.equals(temp.getTitle())) {
                return new ResponseEntity<>("Conflict. Image already inserted!", HttpStatus.CONFLICT);
            }
        } catch ( NullPointerException npe ) {
            imageOfTheDayService.save(temp);
            return new ResponseEntity<>("Image stored.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
