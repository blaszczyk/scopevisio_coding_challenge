package com.github.blaszczyk.scopeviso.postcodeservice.service;

import com.github.blaszczyk.scopeviso.postcodeservice.domain.Location;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RequestMapping("/postcode/api")
@ResponseBody
public interface PostCodeService {

    @GetMapping(path = "/location/{postleitzahl}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity<List<Location>>> getLocations(@PathVariable("postleitzahl") final String plz);
}
