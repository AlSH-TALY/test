package com.example.demo.controller;

import com.example.demo.model.AreqRequest;
import com.example.demo.model.AresResponse;
import com.example.demo.service.AreqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acs/api/3ds2/areq")
public class AreqController {

    private final AreqService areqService;

    @Autowired
    public AreqController(AreqService areqService) {
        this.areqService = areqService;
    }

    @PostMapping
    public ResponseEntity<AresResponse> handleAreq(@RequestBody AreqRequest areqRequest) {
        AresResponse aresResponse = areqService.processAreq(areqRequest);
        
        return ResponseEntity.ok(aresResponse);
    }
}
