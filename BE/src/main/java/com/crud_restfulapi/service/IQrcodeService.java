package com.crud_restfulapi.service;

import com.crud_restfulapi.model.Qrcode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IQrcodeService {
    Qrcode getQrcodeLatest();
    Page<Qrcode> getListQrcode (Pageable pageable);
}
