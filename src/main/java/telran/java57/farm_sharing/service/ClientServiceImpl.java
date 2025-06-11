package telran.java57.farm_sharing.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telran.java57.farm_sharing.dao.ClientRepository;
import telran.java57.farm_sharing.dto.ClientDto;
import telran.java57.farm_sharing.dto.exceptions.EntityNotFoundException;
import telran.java57.farm_sharing.model.Clients;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean addClient(ClientDto clientDto) {
        if ( clientRepository.existsById(clientDto.getId()) ) {
            return false;
        }
        clientRepository.save(modelMapper.map(clientDto, Clients.class));
        return true;
    }

    @Override
    public ClientDto findClientById(Long id) {
        Clients clients = clientRepository.findById(id).orElseThrow(EntityNotFoundException :: new);
        return modelMapper.map(clients, ClientDto.class);
    }

    @Override
    @Transactional
    public ClientDto updateClientName(Long id, String name) {
        Clients clients = clientRepository.findById(id).orElseThrow(EntityNotFoundException :: new);
        clients.setName(name);
        clientRepository.save(clients);
        return modelMapper.map(clients, ClientDto.class);
    }

    @Override
    @Transactional
    public ClientDto updateClientPhoneNumber(Long id, String phoneNumber) {
        Clients clients = clientRepository.findById(id).orElseThrow(EntityNotFoundException :: new);
        clients.setPhoneNumber(phoneNumber);
        clientRepository.save(clients);
        return modelMapper.map(clients, ClientDto.class);

    }

    @Override
    @Transactional
    public ClientDto updateClientCity(Long id, String city) {
        Clients clients = clientRepository.findById(id).orElseThrow(EntityNotFoundException :: new);
        clients.setCity(city);
        clientRepository.save(clients);
        return modelMapper.map(clients, ClientDto.class);

    }

    @Override
    @Transactional
    public ClientDto updateClientEmail(Long id, String email) {
        Clients clients = clientRepository.findById(id).orElseThrow(EntityNotFoundException :: new);
        clients.setEmail(email);
        clientRepository.save(clients);
        return modelMapper.map(clients, ClientDto.class);

    }

    @Override
    @Transactional
    public ClientDto removeClient(Long id) {
        Clients clients = clientRepository.findById(id).orElseThrow(EntityNotFoundException :: new);
        clientRepository.deleteById(clients.getId());
        return modelMapper.map(clients, ClientDto.class);
    }

    @Override
    public List<ClientDto> findClientsByCity(String city) {
        return clientRepository.findByCityIgnoreCase(city)
                .stream()
                .map(c -> modelMapper.map(c, ClientDto.class))
                .toList();
    }

    @Override
    public List<ClientDto> findClientsByName(String name) {
        return clientRepository.findByNameIgnoreCase(name)
                .stream()
                .map(c -> modelMapper.map(c, ClientDto.class))
                .toList();
    }

    @Override
    public List<ClientDto> findClientsByPhoneNumber(String phoneNumber) {
        return clientRepository.findByPhoneNumber(phoneNumber)
                .stream()
                .map(c -> modelMapper.map(c, ClientDto.class))
                .toList();
    }

    @Override
    public List<ClientDto> findClientsByEmail(String email) {
        return clientRepository.findByEmailIgnoreCase(email)
                .stream()
                .map(c -> modelMapper.map(c, ClientDto.class))
                .toList();
    }

    @Override
    public List<ClientDto> findAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, ClientDto.class))
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }
}
