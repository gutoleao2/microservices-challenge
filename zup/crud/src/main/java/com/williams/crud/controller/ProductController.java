package com.williams.crud.controller;

import com.williams.crud.controller.api.ProductControllerAPI;
import com.williams.crud.data.vo.ProductVO;
import com.williams.crud.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/product")
public class ProductController implements ProductControllerAPI {

    private final ProductService productService;
    private final PagedResourcesAssembler<ProductVO> assembler;

    @Autowired
    public ProductController(ProductService productService, PagedResourcesAssembler<ProductVO> assembler) {
        this.productService = productService;
        this.assembler = assembler;
    }

    public ProductVO findById(@PathVariable("id") Long id) {
        ProductVO productVO = productService.findById(id);
        productVO.add(linkTo(methodOn(ProductController.class).findById(id)).withSelfRel());
        return productVO;
    }

    public ResponseEntity<?>  findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "limit", defaultValue = "12") int limit,
                                      @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));

        Page<ProductVO> productVOPage = productService.findAll(pageable);
        productVOPage.stream()
                .forEach(p -> p.add(linkTo(methodOn(ProductController.class).findById(p.getId())).withSelfRel()));

        PagedModel<EntityModel<ProductVO>> pagedModel = assembler.toModel(productVOPage);

        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    public ProductVO create (@RequestBody ProductVO productVO) {
        ProductVO response = productService.create(productVO);
        response.add(linkTo(methodOn(ProductController.class).findById(response.getId())).withSelfRel());
        return response;
    }

    public ProductVO update(@RequestBody ProductVO productVO) {
        ProductVO response = productService.update(productVO);
        response.add(linkTo(methodOn(ProductController.class).findById(response.getId())).withSelfRel());
        return response;
    }

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
