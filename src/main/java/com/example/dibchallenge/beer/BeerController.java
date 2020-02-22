package com.example.dibchallenge.beer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beers")
public class BeerController {

    private final BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping
    public ResponseEntity<Page<BeerDto>> getAll(
            @PageableDefault(page = 0, size = 50)
            @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(BeerMapper.toPageDto(beerService.getAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(BeerMapper.toDto(beerService.getById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        beerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<Long> insertBeers(
            @RequestParam(value = "numberOfBeers", required = false, defaultValue = "10") Long numberOfBeers) {
        return ResponseEntity.ok(beerService.insertBeers(numberOfBeers));
    }
}
