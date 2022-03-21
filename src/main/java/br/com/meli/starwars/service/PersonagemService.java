package br.com.meli.starwars.service;

import br.com.meli.starwars.entity.Personagem;
import br.com.meli.starwars.repository.PersonagemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonagemService {
    private final PersonagemRepository personagemRepository;

    public PersonagemService(PersonagemRepository personagemRepository) {
        this.personagemRepository = personagemRepository;
    }


    public Optional<Personagem> findPersonagemByContainingName(String nome) {
        return personagemRepository.findPersonagemByContainingName(nome);
    }
}
