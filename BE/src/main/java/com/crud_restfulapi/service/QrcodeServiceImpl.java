package com.crud_restfulapi.service;

import com.crud_restfulapi.model.Qrcode;
import com.crud_restfulapi.repository.QrcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QrcodeServiceImpl implements IQrcodeService {
  @Autowired
  private QrcodeRepository qrcodeRepository;

  @Override
  public Qrcode getQrcodeLatest() {
    return qrcodeRepository.getQrcodeLatest();
  }

  @Override
  public Page<Qrcode> getListQrcode(Pageable pageable) {
    Page<Qrcode> qrcodes = qrcodeRepository.findAll(pageable);
    return qrcodes;
  }
}
