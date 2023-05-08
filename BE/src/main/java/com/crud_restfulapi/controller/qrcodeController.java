package com.crud_restfulapi.controller;
import com.crud_restfulapi.model.Qrcode;
import com.crud_restfulapi.model.responseObject;
import com.crud_restfulapi.service.IProductService;
import com.crud_restfulapi.service.IQrcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController // trả về data dạng object, spring sẽ chuyển sang json thay vì dùng @controller trả về dạng template
@RequestMapping(path="/api/qrcode")
public class qrcodeController {

    // @Autowired
    // private IProductService iProductService;
    @Autowired
    private IQrcodeService iQrcodeService;

    @GetMapping("/getLatest")
    ResponseEntity<responseObject> getAll(){
        responseObject a = new responseObject("Done!","Qrcode mới nhất",iQrcodeService.getQrcodeLatest());
        return new ResponseEntity<>( a, HttpStatus.OK);
    }

    @GetMapping(value = "/getListQrcode")
    public ResponseEntity<Page<Qrcode> > searchVoucherWithPage( Pageable pageable , @RequestParam String searchOption)  {
      Pageable pb = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
        Page<Qrcode> page = iQrcodeService.getListQrcode(pb);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
