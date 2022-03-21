package br.com.meli.starwars.repository;

import br.com.meli.starwars.entity.Personagem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public class PersonagemRepository {
    private final List<Personagem> personagens;
    private final ObjectMapper mapper;
    private static final String STARWARS_JSON_PATH = "src/main/resources/static/starwars.json";

    public PersonagemRepository(ObjectMapper mapper) {
        this.mapper = mapper;
        this.personagens = new ArrayList<>();
        initializePersonagens();
    }

    private void initializePersonagens() {
        try {
            TypeReference<List<Personagem>> listTypeReference = new TypeReference<>() {};
            List<Personagem> readPersonagens = mapper.readValue(Paths.get(STARWARS_JSON_PATH).toFile(), listTypeReference);
            personagens.addAll(readPersonagens);
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public Optional<Personagem> findPersonagemByContainingName(String name) {
        return this.personagens.stream().filter(p -> p.getName()
                .toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))).findFirst();
    }
}
