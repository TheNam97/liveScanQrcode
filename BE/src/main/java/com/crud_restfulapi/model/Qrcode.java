package com.crud_restfulapi.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "qrcode")
public class Qrcode {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "ItemCode")
  private String itemCode;
  @Column(name = "Time")
  private LocalDateTime time;
  @Column(name = "SenderAddress")
  private String senderAddress;
  @Column(name = "ReceiverAddress")
  private String receiverAddress;
  @Column(name = "POSCode")
  private String POSCode;
  @Column(name = "imgQrcode")
  private String imgQrcode;
  @Column(name = "imgGoods")
  private String imgGoods;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  public String getSenderAddress() {
    return senderAddress;
  }

  public void setSenderAddress(String senderAddress) {
    this.senderAddress = senderAddress;
  }

  public String getReceiverAddress() {
    return receiverAddress;
  }

  public void setReceiverAddress(String receiverAddress) {
    this.receiverAddress = receiverAddress;
  }

  public String getPOSCode() {
    return POSCode;
  }

  public void setPOSCode(String POSCode) {
    this.POSCode = POSCode;
  }

  public String getImgQrcode() {
    return imgQrcode;
  }

  public void setImgQrcode(String imgQrcode) {
    this.imgQrcode = imgQrcode;
  }

  public String getImgGoods() {
    return imgGoods;
  }

  public void setImgGoods(String imgGoods) {
    this.imgGoods = imgGoods;
  }
}
