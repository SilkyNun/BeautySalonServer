package project.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.entity.Client;
import project.entity.Master;
import project.exceptions.NotFoundException;
import project.repository.ClientRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        Optional<Client> master = clientRepository.findById(id);
        if (master.isEmpty()) {
            throw new NotFoundException(String.format("Client is not found", id));
        }
        return master.get();
    }

    public Client updateClient(Client client) {
        Client clientToUpdate = getClientById(client.getId());
        clientToUpdate.setName(client.getName() == null ? clientToUpdate.getName() : client.getName());
        clientToUpdate.setTel(client.getTel() == null ? clientToUpdate.getTel() : client.getTel());
        clientToUpdate.setOrders(client.getOrders() == null ? clientToUpdate.getOrders() : client.getOrders());
        return clientRepository.save(clientToUpdate);
    }

    public void removeClient(Client client) {
        clientRepository.delete(client);
    }

}
