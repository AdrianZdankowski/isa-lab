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

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
@Order(2)
public class CommandLineApp implements CommandLineRunner {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private ArchetypeService archetypeService;

    @Autowired
    private NatureElementService natureElementService;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        System.out.println("*****Character database*****");
        String command;

        do {
            System.out.println("For available commands type 'help'");
            System.out.println("Type a command: ");
            command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "quit":
                    System.out.println("Quiting the application.");
                    break;
                case "help":
                    listCommands();
                    break;
                case "archetypes":
                    listArchetypes();
                    break;
                case "characters":
                    listCharacters();
                    break;
                case "elements":
                    listElements();
                    break;
                case "add":
                    addCharacter(scanner);
                    break;
                case "delete":
                    deleteCharacter(scanner);
                    break;
                case "list":
                    listArchetypesWithCharacters();
                    break;
                default:
                    System.out.println("Unknown command!");
            }
        } while (!command.equals("quit"));
    }

    private void listCommands() {
        System.out.println("Available commands:");
        System.out.println("1. help - list available commands");
        System.out.println("2. list - list archetypes and characters within them");
        System.out.println("2. archetypes - list all archetypes");
        System.out.println("3. characters - list all characters");
        System.out.println("4. elements - list all natures elements");
        System.out.println("6. add - add new character");
        System.out.println("7. delete - delete existing character");
        System.out.println("8. quit - quit the application");
    }

    private void listArchetypes() {
        List<Archetype> archetypes = archetypeService.getAllArchetypes();
        System.out.println("All archetypes:");
        archetypes.forEach(archetype -> {
            System.out.println("* " + archetype.getName());
        });
    }

    private void listArchetypesWithCharacters() {
        List<Archetype> archetypes = archetypeService.getAllArchetypesWithCharactersAndElements();

        archetypes.forEach(archetype -> {
            System.out.println("Archetype: " + archetype.getName());
            List<Character> characters = archetype.getCharacterList();
            System.out.println(" Characters:");
            characters.forEach(character -> {
                System.out.println("  * " + character.getName() + " [power level: " + character.getPowerLevel() + " , element: " + character.getElement().getElementName() + "]");
            });
        });


    }

    private void listCharacters() {
       List<Character> characters = characterService.getAllCharacters();
       System.out.println("All characters:");
       characters.forEach(c -> System.out.println("* " + c.toString()));
    }

    private void listElements() {
        List<NatureElement> elements = natureElementService.getAllNatureElements();
        System.out.println("All elements:");
        elements.forEach(e -> System.out.println("* " + e.toString()));

    }

    private void addCharacter(Scanner scanner) {
        System.out.println("Add new character:");

        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Power level: ");
        int powerLevel = Integer.parseInt(scanner.nextLine());

        System.out.println("Archetypes:");
        List<Archetype> archetypes = archetypeService.getAllArchetypes();
        for (int i = 0; i < archetypes.size(); i++) {
            System.out.println(i + ". " + archetypes.get(i).getName());
        }

        int archetypeNumber = -1;
        while (true) {
            archetypeNumber = Integer.parseInt(scanner.nextLine());
            if (archetypeNumber >= 0 && archetypeNumber < archetypes.size()) {
                break;
            }
            System.out.println("Incorrect archetype! Try again:");
        }

        Archetype archetype = archetypes.get(archetypeNumber);

        System.out.println("Elements:");
        List<NatureElement> elements = natureElementService.getAllNatureElements();
        for (int i = 0; i < elements.size(); i++) {
            System.out.println(i + ". " + elements.get(i).getElementName());
        }

        int elementNumber = -1;
        while(true) {
            elementNumber = Integer.parseInt(scanner.nextLine());
            if (elementNumber >= 0 && elementNumber < elements.size()) {
                break;
            }
            System.out.println("Incorrect element! Try again:");
        }

        NatureElement element = elements.get(elementNumber);

        Character newCharacter = Character.builder()
                .id(UUID.randomUUID()).name(name)
                .powerLevel(powerLevel).archetype(archetype)
                .element(element).build();

        characterService.saveCharacter(newCharacter);
        System.out.println("Added: " + newCharacter.toString());
    }

    private void deleteCharacter(Scanner scanner) {
        System.out.println("Existing characters:");
        List<Character> existingCharacters = characterService.getAllCharacters();
        for (int i = 0; i < existingCharacters.size(); i++) {
            System.out.println(i + ". " + existingCharacters.get(i).toString());
        }

        int characterNumber = -1;
        while (true) {
            characterNumber = Integer.parseInt(scanner.nextLine());
            if (characterNumber >= 0 && characterNumber < existingCharacters.size()) {
                break;
            }
            System.out.println("Incorrect character index! Try again:");
        }

        Character character = existingCharacters.get(characterNumber);

        characterService.deleteCharacter(character.getId());
        System.out.println("Deleted " + character.toString());
    }
}
