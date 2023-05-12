package com.crud_restfulapi.controller;
import com.crud_restfulapi.model.ResponseObject;
import com.crud_restfulapi.model.Product;
import com.crud_restfulapi.model.zalo;
import com.crud_restfulapi.service.IProductService;
import com.crud_restfulapi.service.IQrcodeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController // trả về data dạng object, spring sẽ chuyển sang json thay vì dùng @controller trả về dạng template
@RequestMapping(path="/api/products")
public class ProductController {

    @Autowired
    private IProductService iProductService;
    @Autowired
    private IQrcodeService iQrcodeService;

    @GetMapping
    List<Product> getAllProducts(){
        return iProductService.getAllProduct();
    }
//    @GetMapping("/getall")
//    ResponseEntity<List<Product>> getAll(){
//        return new ResponseEntity<List<Product>>( iProductService.getAllProduct(), HttpStatus.OK);
//    }
    @GetMapping("/getall")
    ResponseEntity<ResponseObject> getAll(){
        ResponseObject a = new ResponseObject("Done!","Đã tìm thấy các sản phẩm",iQrcodeService.getQrcodeLatest());
        return new ResponseEntity<>( a, HttpStatus.OK);
    }
    @GetMapping("/getalll")
    ResponseEntity<ResponseObject> getAlll() throws IOException {

        //https://stackoverflow.com/questions/66277274/how-to-send-body-in-resttemplate-as-application-x-www-form-urlencoded
//        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
//        requestBody.add("username","aaa");
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("oke", "a");
//        HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);

        //https://stackoverflow.com/questions/38372422/how-to-post-form-data-with-spring-resttemplate
        String url = "https://oauth.zaloapp.com/v4/oa/access_token";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headerss = new HttpHeaders();

        headerss.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headerss.add("Content-Type", "application/x-www-form-urlencoded");
        headerss.add("secret_key", "5z0HhFLFIxhYWI0KbQFH");

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("refresh_token", "1SjbQqF_iZeXo5X2QCBQFIpb5b5utSCz1keBK1JLiZLhrdPJEkYTNbdV8ZCdokbQ2ieiErJjnsjvs7qN8U-yV7VKN0SFoiTOSum-16oUmcCZcnCo2wAWLd-hRm0uglnQGPnN0Wx1BMj-qzu70BlH9TssMdcRflnhgufg0e2HjGELaHrro8kdCERd3mRUhw08lSe90oOtIt4s6ipkDm");
        map.add("app_id", "2525871523867641187");
        map.add("grant_type", "refresh_token");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headerss);
//        JsonObject a = new JsonObject();
        ResponseEntity<String> aaa = restTemplate.postForEntity( url, request , String.class );
        ObjectMapper mapper = new ObjectMapper();
        zalo mapp = mapper.readValue(aaa.getBody(), zalo.class);
//        Object response = restTemplate.postForObject( url, request , Object.class );

        //https://stackoverflow.com/questions/40574892/how-to-send-post-request-with-x-www-form-urlencoded-body
//        HttpHeaders headersss = new HttpHeaders();
//        headersss.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        MultiValueMap<String, String> mapp= new LinkedMultiValueMap<String, String>();
//        mapp.add("email", "first.last@example.com");
//        HttpEntity<MultiValueMap<String, String>> requestt = new HttpEntity<MultiValueMap<String, String>>(mapp, headersss);
//        ResponseEntity<String> responsee = restTemplate.postForEntity( url, requestt , String.class );

        ResponseObject a = new ResponseObject("Done!","Đã tìm thấy các sản phẩm",iProductService.getAllProduct());
        return new ResponseEntity<>( a, HttpStatus.OK);
    }


    @GetMapping("/{id}")  //Địa chỉ nhận request
    ResponseEntity<ResponseObject> findById(@PathVariable long id){
        Optional<Product> foundProduct= iProductService.getOneProduct(id);
        if (foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Done!","Da tim thay id="+id,foundProduct)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Fail","Khong tim thay id="+id,""));
        }
    }
    //Postman: raw,json
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product product){
        List<Product> products = iProductService.findByProductName(product.getProductName());
        if (products.size()==0) {
            iProductService.addProduct(product);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Done!", "Da them Product", product)
            );
        } else {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Fail", "Product da ton tai", "")
        );}
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteById(@PathVariable long id){
        Optional<Product> foundProduct= iProductService.getOneProduct(id);

        if (foundProduct.isPresent()){
            iProductService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Done!","Da xoa id="+id,foundProduct)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Fail","Khong tim thay id="+id,""));
        }
    }
    @PutMapping("{id}")
    ResponseEntity<ResponseObject> updateProduct(@PathVariable long id, @RequestBody Product product ){
        Optional<Product> foundProduct = iProductService.getOneProduct(id);
        if (foundProduct.isPresent()){
            iProductService.updateProduct(id,product);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Done!","Da cap nhat id="+id,product)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Fail","Khong tim thay id="+id,""));
        }
    }
}
