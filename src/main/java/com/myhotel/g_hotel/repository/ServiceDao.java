package com.myhotel.g_hotel.repository;

import com.myhotel.g_hotel.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDao extends JpaRepository<Service,Long> {

    Service findByName(String name);

}
