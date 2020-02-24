package com.example.dibchallenge.beer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(value="Beer management", tags = "Beer controller")
@RestController
@RequestMapping("/beers")
public class BeerController {

    private final BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @ApiOperation(value = "Get page of beers.", response = Page.class)
    @ApiResponse(code = 200, message = "OK")
    @GetMapping
    public ResponseEntity<Page<BeerDto>> getAll(
            @PageableDefault(page = 0, size = 50)
            @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(BeerMapper.toPageDto(beerService.getAll(pageable)));
    }

    @ApiOperation(value = "Get beer by ID.", response = BeerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Beer not found!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BeerDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(BeerMapper.toDto(beerService.getById(id)));
    }

    @ApiOperation("Remove beer by ID.")
    @ApiResponse(code = 204, message = "No Content")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        beerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Insert beers from Punk API.")
    @ApiResponse(code = 200, message = "OK")
    @PostMapping("/insert-from-punk-api")
    public ResponseEntity<Long> insertBeers(
            @ApiParam(value = "Number of beers to insert in DB.", required = false)
            @RequestParam(value = "numberOfBeers", required = false, defaultValue = "10") Long numberOfBeers) {
        return ResponseEntity.ok(beerService.insertBeersFromPunkApi(numberOfBeers));
    }
}
