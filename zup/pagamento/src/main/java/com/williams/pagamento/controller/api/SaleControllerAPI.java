package com.williams.pagamento.controller.api;

import com.williams.pagamento.data.vo.SaleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Api(value = "/sale", produces = "REST API for Sale")
public interface SaleControllerAPI {

    @ResponseBody
    @Transactional
    @ApiOperation(value = "Find one Sale")
    @GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
    public SaleVO findById(@PathVariable("id") Long id);

    @ResponseBody
    @Transactional
    @ApiOperation(value = "Find all sales")
    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "12") int limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction);


    @ResponseBody
    @Transactional
    @ApiOperation(value = "Create one Sale")
    @PostMapping(produces = {"application/json","application/xml","application/x-yaml"},
            consumes = {"application/json","application/xml","application/x-yaml"})
    public SaleVO create (@RequestBody SaleVO productVO);
}
