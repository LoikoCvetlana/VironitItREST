package com.sportoras.web.controller;

import com.sportoras.database.entity.Material;
import com.sportoras.service.dto.Material.MaterialDto;
import com.sportoras.service.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping(value = "/materials", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<MaterialDto>> listAllMaterials() {
        return new ResponseEntity<>(materialService.findAllMaterials(), HttpStatus.OK);
    }

    @PostMapping(value = "/material-save", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Material> saveMaterial(@RequestBody MaterialDto materialDto) {
        materialService.saveMaterial(materialDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/material-info/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Material> getMaterialById(@PathVariable("id") long id) {
        return new ResponseEntity<>(materialService.findById(id), HttpStatus.OK);
    }
}
