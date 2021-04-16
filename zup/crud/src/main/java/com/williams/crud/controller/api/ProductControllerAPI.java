package com.williams.crud.controller.api;

import com.williams.crud.data.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Api(value = "/product", produces = "REST API for Product")
public interface ProductControllerAPI {

    @ResponseBody
    @Transactional
    @ApiOperation(value = "Find one Product")
    @GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
    public ProductVO findById(@PathVariable("id") Long id);

    @ResponseBody
    @Transactional
    @ApiOperation(value = "Find all Products")
    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?>  findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "limit", defaultValue = "12") int limit,
                                      @RequestParam(value = "direction", defaultValue = "asc") String direction);

    @ResponseBody
    @Transactional
    @ApiOperation(value = "Create Product")
    @PostMapping(produces = {"application/json","application/xml","application/x-yaml"},
            consumes = {"application/json","application/xml","application/x-yaml"})
    public ProductVO create (@RequestBody ProductVO productVO) ;

    @ResponseBody
    @Transactional
    @ApiOperation(value = "Update Product")
    @PutMapping(produces = {"application/json","application/xml","application/x-yaml"},
            consumes = {"application/json","application/xml","application/x-yaml"})
    public ProductVO update(@RequestBody ProductVO productVO);

    @ResponseBody
    @Transactional
    @ApiOperation(value = "Delete Product by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id);



}
