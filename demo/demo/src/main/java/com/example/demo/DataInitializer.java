package com.example.demo;

import com.example.demo.character.entity.Archetype;
import com.example.demo.character.entity.Character;
import com.example.demo.character.entity.NatureElement;
import com.example.demo.character.service.ArchetypeService;
import com.example.demo.character.service.CharacterService;
import com.example.demo.character.service.NatureElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {
    private final CharacterService characterService;
    private final ArchetypeService archetypeService;
    private final NatureElementService natureElementService;


    @Autowired
    public DataInitializer(CharacterService characterService,
                           ArchetypeService archetypeService,
                           NatureElementService natureElementService) {
        this.characterService = characterService;
        this.archetypeService = archetypeService;
        this.natureElementService = natureElementService;
    }


    @Override
    public void run(String... args) throws Exception {
        Archetype titanArchetype = Archetype.builder().id(UUID.randomUUID()).name("Titan").baseHealth(200).baseDamage(100).build();
        Archetype warlockArchetype = Archetype.builder().id(UUID.randomUUID()).name("Warlock").baseHealth(150).baseDamage(125).build();
        Archetype hunterArchetype = Archetype.builder().id(UUID.randomUUID()).name("Hunter").baseHealth(100).baseDamage(150).build();

        titanArchetype = archetypeService.saveArchetype(titanArchetype);
        warlockArchetype = archetypeService.saveArchetype(warlockArchetype);
        hunterArchetype = archetypeService.saveArchetype(hunterArchetype);

        NatureElement solar = NatureElement.builder().id(UUID.randomUUID()).elementName("Solar").elementSide("Light").build();
        NatureElement arc = NatureElement.builder().id(UUID.randomUUID()).elementName("Arc").elementSide("Light").build();
        NatureElement voidClass = NatureElement.builder().id(UUID.randomUUID()).elementName("Void").elementSide("Light").build();
        NatureElement stasis = NatureElement.builder().id(UUID.randomUUID()).elementName("Stasis").elementSide("Darkness").build();

        solar = natureElementService.saveNatureElement(solar);
        arc = natureElementService.saveNatureElement(arc);
        voidClass = natureElementService.saveNatureElement(voidClass);
        stasis = natureElementService.saveNatureElement(stasis);


        List<Character> characterList = new ArrayList<>();

        characterList.add(Character.builder().id(UUID.randomUUID()).name("Wiesio").powerLevel(7).archetype(titanArchetype).element(solar).build());
        characterList.add(Character.builder().id(UUID.randomUUID()).name("Maria").powerLevel(1).archetype(titanArchetype).element(arc).build());
        characterList.add(Character.builder().id(UUID.randomUUID()).name("Maurycy").powerLevel(10).archetype(warlockArchetype).element(voidClass).build());
        characterList.add(Character.builder().id(UUID.randomUUID()).name("Joanna").powerLevel(6).archetype(warlockArchetype).element(stasis).build());
        characterList.add(Character.builder().id(UUID.randomUUID()).name("Lech").powerLevel(4).archetype(hunterArchetype).element(arc).build());
        characterList.add(Character.builder().id(UUID.randomUUID()).name("Alice").powerLevel(2).archetype(hunterArchetype).element(solar).build());

        characterList.forEach(characterService::saveCharacter);

        for (Character character : characterList) {
            switch (character.getArchetype().getName()) {
                case "Titan":
                    titanArchetype.addToList(character);
                    break;
                case "Warlock":
                    warlockArchetype.addToList(character);
                    break;
                case "Hunter":
                    hunterArchetype.addToList(character);
                    break;
            }
        }

        archetypeService.saveArchetype(titanArchetype);
        archetypeService.saveArchetype(warlockArchetype);
        archetypeService.saveArchetype(hunterArchetype);

        System.out.println("Data has been initialized");


    }
}
