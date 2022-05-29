package com.devsuperior.bdsclient.repository;

import com.devsuperior.bdsclient.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
