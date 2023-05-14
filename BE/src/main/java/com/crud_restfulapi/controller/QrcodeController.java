package com.crud_restfulapi.controller;
import com.crud_restfulapi.model.Qrcode;
import com.crud_restfulapi.model.ResponseObject;
import com.crud_restfulapi.service.IQrcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController // trả về data dạng object, spring sẽ chuyển sang json thay vì dùng @controller trả về dạng template
@RequestMapping(path = "/api/qrcode")
public class QrcodeController {

  @Autowired
  private IQrcodeService iQrcodeService;

  @GetMapping("/getLatest/{itemCode}")
  ResponseEntity<ResponseObject> getAll(@PathVariable String itemCode) throws UnsupportedFlavorException, IOException, AWTException {
    Qrcode qrcode = iQrcodeService.getQrcodeLatest();
    ResponseObject responseObject = new ResponseObject("Done!", "Qrcode mới nhất", qrcode);
    System.setProperty("java.awt.headless", "false");
    if(!qrcode.getItemCode().equals(itemCode)){
      String testString = qrcode.getItemCode();
      String str = testString;
      Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
      StringSelection strse1 = new StringSelection(str);
      clip.setContents(strse1, strse1);
      Robot robot = new Robot();
      // robot.mouseMove(1000,1000);
//      robot.mouseMove(1000,1000);
      robot.delay(2000);
      robot.mousePress(InputEvent.BUTTON1_MASK);
      // robot.delay(50);
      robot.mouseRelease(InputEvent.BUTTON1_MASK);
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_V);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
    }
    return new ResponseEntity<>(responseObject, HttpStatus.OK);
  }

  @GetMapping(value = "/getListQrcode")
  public ResponseEntity<Page<Qrcode>> searchVoucherWithPage(Pageable pageable, @RequestParam String searchOption) {
    Pageable pb = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
    Page<Qrcode> page = iQrcodeService.getListQrcode(pb);
    return new ResponseEntity<>(page, HttpStatus.OK);
  }
}
