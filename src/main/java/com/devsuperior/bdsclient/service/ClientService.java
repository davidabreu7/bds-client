package com.devsuperior.bdsclient.service;

import com.devsuperior.bdsclient.dto.ClientDto;
import com.devsuperior.bdsclient.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Page<ClientDto> findAll(Pageable page) {
        return repository.findAll(page).map(ClientDto::new);
    }

}
