package farm_sharing.client.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import farm_sharing.client.dao.ClientRepository;
import farm_sharing.client.dto.ClientDto;
import farm_sharing.client.dto.NewClientDto;
import farm_sharing.client.dto.exceptions.EntityNotFoundException;
import farm_sharing.client.model.Client;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean addClient(NewClientDto newClientDto) {
        if ( clientRepository.existsClientByPhoneNumber(newClientDto.getPhoneNumber()) || clientRepository.existsClientByEmail(newClientDto.getEmail())) {
            return false; // Client with this phone number already exists
        }
        clientRepository.save(modelMapper.map(newClientDto, Client.class));
        return true;
    }

    @Override
    public ClientDto findClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException :: new);
        return modelMapper.map(client, ClientDto.class);
    }

    @Override
    @Transactional
    public ClientDto updateClientName(Long id, String name) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException :: new);
        client.setName(name);
        clientRepository.save(client);
        return modelMapper.map(client, ClientDto.class);
    }

    @Override
    @Transactional
    public ClientDto updateClientPhoneNumber(Long id, String phoneNumber) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException :: new);
        client.setPhoneNumber(phoneNumber);
        clientRepository.save(client);
        return modelMapper.map(client, ClientDto.class);

    }

    @Override
    @Transactional
    public ClientDto updateClientCity(Long id, String city) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException :: new);
        client.setCity(city);
        clientRepository.save(client);
        return modelMapper.map(client, ClientDto.class);

    }

    @Override
    @Transactional
    public ClientDto updateClientEmail(Long id, String email) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException :: new);
        client.setEmail(email);
        clientRepository.save(client);
        return modelMapper.map(client, ClientDto.class);

    }

    @Override
    @Transactional
    public ClientDto removeClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException :: new);
        clientRepository.deleteById(client.getId());
        return modelMapper.map(client, ClientDto.class);
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
