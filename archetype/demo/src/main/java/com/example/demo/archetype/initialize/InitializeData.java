package com.example.demo.archetype.initialize;

import com.example.demo.archetype.entity.Archetype;
import com.example.demo.archetype.service.ArchetypeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {

    private final ArchetypeService archetypeService;

    @Autowired
    public InitializeData(ArchetypeService archetypeService) {
        this.archetypeService = archetypeService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (archetypeService.getAllArchetypes().isEmpty()) {
            Archetype titan = Archetype.builder()
                    .id(UUID.fromString("e21ada98-ab4b-4b29-ad87-aa1cc85ef729"))
                    .name("Titan")
                    .baseDamage(100)
                    .baseHealth(150)
                    .build();

            Archetype warlock = Archetype.builder()
                    .id(UUID.fromString("813ac659-c1fc-4636-be9f-0f1a1ffb9bb5"))
                    .name("Warlock")
                    .baseDamage(125)
                    .baseHealth(125)
                    .build();

            Archetype hunter = Archetype.builder()
                    .id(UUID.fromString("07063210-e9df-4cda-afef-14cebd4ece5c"))
                    .name("Hunter")
                    .baseDamage(150)
                    .baseHealth(100)
                    .build();

            archetypeService.saveArchetype(titan);
            archetypeService.saveArchetype(warlock);
            archetypeService.saveArchetype(hunter);
        }
    }
}
