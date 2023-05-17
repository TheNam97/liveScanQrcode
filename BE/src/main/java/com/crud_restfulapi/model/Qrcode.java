package com.crud_restfulapi.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "qrcode")
public class Qrcode {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "itemcode")
  private String itemCode;
  @Column(name = "Time")
  private LocalDateTime time;
  @Column(name = "senderaddress")
  private String senderAddress;
  @Column(name = "receiveraddress")
  private String receiverAddress;
  @Column(name = "poscode")
  private String POSCode;
  @Column(name = "imgqrcode")
  private String imgQrcode;
  @Column(name = "imggoods")
  private String imgGoods;

  @Column(name = "acceptanceposcode")
  private Long acceptancePOSCode;
  @Column(name = "weight")
  private Long weight;
  @Column(name = "receiveraddresscode")
  private Long receiverAddressCode;
  @Column(name = "servicecode")
  private String serviceCode;
  @Column(name = "speaker")
  private String speaker;
  @Column(name = "groupid")
  private String groupId;

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public Long getAcceptancePOSCode() {
    return acceptancePOSCode;
  }

  public void setAcceptancePOSCode(Long acceptancePOSCode) {
    this.acceptancePOSCode = acceptancePOSCode;
  }

  public Long getWeight() {
    return weight;
  }

  public void setWeight(Long weight) {
    this.weight = weight;
  }

  public Long getReceiverAddressCode() {
    return receiverAddressCode;
  }

  public void setReceiverAddressCode(Long receiverAddressCode) {
    this.receiverAddressCode = receiverAddressCode;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public String getSpeaker() {
    return speaker;
  }

  public void setSpeaker(String speaker) {
    this.speaker = speaker;
  }

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
