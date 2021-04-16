package com.williams.pagamento.controller;

import com.williams.pagamento.controller.api.SaleControllerAPI;
import com.williams.pagamento.data.vo.SaleVO;
import com.williams.pagamento.services.SaleService;
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
@RequestMapping("/sale")
public class SaleController implements SaleControllerAPI {

    private final SaleService saleService;
    private final PagedResourcesAssembler<SaleVO> assembler;

    @Autowired
    public SaleController(SaleService saleService, PagedResourcesAssembler<SaleVO> assembler) {
        this.saleService = saleService;
        this.assembler = assembler;
    }

    public SaleVO findById(@PathVariable("id") Long id) {
        SaleVO productVO = saleService.findById(id);
        productVO.add(linkTo(methodOn(SaleController.class).findById(id)).withSelfRel());
        return productVO;
    }

    public ResponseEntity<?>  findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "limit", defaultValue = "12") int limit,
                                      @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "date"));

        Page<SaleVO> productVOPage = saleService.findAll(pageable);
        productVOPage.stream()
                .forEach(p -> p.add(linkTo(methodOn(SaleController.class).findById(p.getId())).withSelfRel()));

        PagedModel<EntityModel<SaleVO>> pagedModel = assembler.toModel(productVOPage);

        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    public SaleVO create (@RequestBody SaleVO productVO) {
        SaleVO response = saleService.create(productVO);
        response.add(linkTo(methodOn(SaleController.class).findById(response.getId())).withSelfRel());
        return response;
    }

}
