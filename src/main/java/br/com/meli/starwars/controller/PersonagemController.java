package br.com.meli.starwars.controller;

import br.com.meli.starwars.entity.Personagem;
import br.com.meli.starwars.service.PersonagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonagemController {
    private final PersonagemService personagemService;

    public PersonagemController(PersonagemService personagemService) {
        this.personagemService = personagemService;
    }

    @GetMapping("/{nome}")
    public ResponseEntity<?> findPersonagemByName(@PathVariable String nome) {
        Optional<Personagem> optionalPersonagem = personagemService.findPersonagemByContainingName(nome);

        if (optionalPersonagem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalPersonagem.get());
    }
}
