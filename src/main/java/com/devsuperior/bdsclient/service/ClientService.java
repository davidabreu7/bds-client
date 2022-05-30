package com.devsuperior.bdsclient.service;

import com.devsuperior.bdsclient.dto.ClientDto;
import com.devsuperior.bdsclient.entity.Client;
import com.devsuperior.bdsclient.exception.DatabaseIntegrityException;
import com.devsuperior.bdsclient.exception.ResourceNotFoundException;
import com.devsuperior.bdsclient.repository.ClientRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClientService {

    final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Page<ClientDto> findAll(Pageable page) {
        return repository.findAll(page).map(ClientDto::new);
    }

    public ClientDto findById(Long id) {
        return repository.findById(id).map(ClientDto::new).orElseThrow(() -> new ResourceNotFoundException("Resource Id: %d not found".formatted(id)));
    }

    @Transactional
    public ClientDto insert(ClientDto dto) {
        return new ClientDto(repository.save(new Client(dto)));
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Category id: %d not found".formatted(id));
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseIntegrityException("Database Error");
        }
    }


    public ClientDto update(Long id, ClientDto dto) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource id: %d not found".formatted(id)));
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setBirthDate(dto.getBirthDate());
        client.setIncome(dto.getIncome());
        client.setChildren(dto.getChildren());
        return new ClientDto(repository.save(client));
    }
}
