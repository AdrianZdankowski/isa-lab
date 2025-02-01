package com.example.demo.character.initialize;

import com.example.demo.character.entity.Archetype;
import com.example.demo.character.entity.Character;
import com.example.demo.character.entity.NatureElement;
import com.example.demo.character.service.ArchetypeService;
import com.example.demo.character.service.CharacterService;
import com.example.demo.character.service.NatureElementService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {
    private final CharacterService characterService;
    private final ArchetypeService archetypeService;
    private final NatureElementService natureElementService;

    @Autowired
    public InitializeData(CharacterService characterService,
                          ArchetypeService archetypeService,
                          NatureElementService natureElementService)
    {
     this.characterService = characterService;
     this.archetypeService = archetypeService;
     this.natureElementService = natureElementService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (characterService.getAllCharacters().isEmpty()) {
            Archetype titan = Archetype.builder()
                    .id(UUID.fromString("e21ada98-ab4b-4b29-ad87-aa1cc85ef729"))
                    .build();

            Archetype warlock = Archetype.builder()
                    .id(UUID.fromString("813ac659-c1fc-4636-be9f-0f1a1ffb9bb5"))
                    .build();

            Archetype hunter = Archetype.builder()
                    .id(UUID.fromString("07063210-e9df-4cda-afef-14cebd4ece5c"))
                    .build();


            archetypeService.saveArchetype(titan);
            archetypeService.saveArchetype(warlock);
            archetypeService.saveArchetype(hunter);

            NatureElement solar = NatureElement.builder()
                    .id(UUID.fromString("83bdf2ce-ae92-428f-bda3-d5838053e2b7"))
                    .build();

            NatureElement arc = NatureElement.builder()
                    .id(UUID.fromString("2044fbd5-91e9-41c2-ad9f-6d806eb9eaf4"))
                    .build();

            NatureElement voidElement = NatureElement.builder()
                    .id(UUID.fromString("24e1822a-35ef-415c-9720-0cb3e1737b82"))
                    .build();

            NatureElement stasis = NatureElement.builder()
                    .id(UUID.fromString("d1f24efd-e0b1-4c39-9d8d-a2511bbd4742"))
                    .build();

            natureElementService.saveNatureElement(solar);
            natureElementService.saveNatureElement(arc);
            natureElementService.saveNatureElement(voidElement);
            natureElementService.saveNatureElement(stasis);


            Character wiesio = Character.builder().id(UUID.randomUUID()).name("Wiesio").powerLevel(7).archetype(titan).element(solar).build();
            Character maria = Character.builder().id(UUID.randomUUID()).name("Maria").powerLevel(1).archetype(titan).element(arc).build();
            Character maurycy = Character.builder().id(UUID.randomUUID()).name("Maurycy").powerLevel(10).archetype(warlock).element(voidElement).build();
            Character joanna = Character.builder().id(UUID.randomUUID()).name("Joanna").powerLevel(6).archetype(warlock).element(stasis).build();
            Character lech = Character.builder().id(UUID.randomUUID()).name("Lech").powerLevel(4).archetype(hunter).element(arc).build();
            Character alice = Character.builder().id(UUID.randomUUID()).name("Alice").powerLevel(2).archetype(hunter).element(solar).build();

            characterService.saveCharacter(wiesio);
            characterService.saveCharacter(maria);
            characterService.saveCharacter(maurycy);
            characterService.saveCharacter(joanna);
            characterService.saveCharacter(lech);
            characterService.saveCharacter(alice);

            System.out.println("Data has been initialized");
        }
    }
}
