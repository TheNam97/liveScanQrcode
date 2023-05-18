package com.crud_restfulapi.repository;

import com.crud_restfulapi.model.Qrcode;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface QrcodeRepository extends JpaRepository<Qrcode , Long> {
    //value = "select * from Bank b where b.CompanyID = ?1 AND isActive = 1 ORDER BY BankCode"
    //SELECT * FROM product LIMIT 1 OFFSET 0;
    //SELECT * FROM product ORDER BY id DESC LIMIT 1;
    @Query( nativeQuery = true, value = "SELECT * FROM qrcode ORDER BY id DESC LIMIT 1")
    Qrcode getQrcodeLatest();
    //SELECT sum(Weight) FROM qrcode WHERE groupid IS NULL
    @Query( nativeQuery = true, value = "SELECT sum(Weight) FROM qrcode WHERE groupid IS NULL or groupid = ''")
    Long sumWeight();
    @Modifying
    @Query( nativeQuery = true, value = "UPDATE qrcode SET groupid = ?1 WHERE groupid is null or groupid = '' ")
    void updateGroupId(String id);

}
