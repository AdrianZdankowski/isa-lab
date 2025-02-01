package com.example.demo;

import com.example.demo.character.dto.CharacterDTO;
import com.example.demo.character.entity.Archetype;
import com.example.demo.character.entity.Character;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		List<Archetype> archetypeList = new ArrayList<>();

		Archetype titanArchetype = Archetype.builder().name("Titan").baseHealth(200).baseDamage(100).build();
		Archetype warlockArchetype = Archetype.builder().name("Warlock").baseHealth(150).baseDamage(125).build();
		Archetype hunterArchetype = Archetype.builder().name("Hunter").baseHealth(100).baseDamage(150).build();

		archetypeList.add(titanArchetype);
		archetypeList.add(warlockArchetype);
		archetypeList.add(hunterArchetype);

		titanArchetype.addToList(Character.builder().name("Wiesio").level(7).archetype(titanArchetype).build());
		titanArchetype.addToList(Character.builder().name("Maria").level(1).archetype(titanArchetype).build());
		warlockArchetype.addToList(Character.builder().name("Maurycy").level(10).archetype(warlockArchetype).build());
		warlockArchetype.addToList(Character.builder().name("Joanna").level(6).archetype(warlockArchetype).build());
		hunterArchetype.addToList(Character.builder().name("Lech").level(4).archetype(hunterArchetype).build());
		hunterArchetype.addToList(Character.builder().name("Alice").level(2).archetype(hunterArchetype).build());

		System.out.println();
		System.out.println("Task #2");
		archetypeList.forEach(archetype -> {
			System.out.println("Archetype: " + archetype.getName());
			archetype.getCharacterList().forEach(character -> System.out.println(character));
		});
		System.out.println();

		System.out.println("Task #3");
		Set<Character> characterSet = archetypeList.stream().flatMap(archetype -> archetype.getCharacterList().stream())
				.collect(Collectors.toSet());
		characterSet.forEach(System.out::println);

		System.out.println();
		System.out.println("Task #4");
		archetypeList.stream().flatMap(archetype -> archetype.getCharacterList().stream())
				.filter(character -> character.getLevel() > 5).sorted(Comparator.comparing(Character::getName))
				.forEach(System.out::println);

		System.out.println();
		System.out.println("Task #5");
		List<CharacterDTO> dtoCharacters = archetypeList.stream().flatMap(archetype -> archetype.getCharacterList().stream())
				.map(character -> new CharacterDTO(character.getName(), character.getLevel(), character.getArchetype().getName()))
				.sorted(Comparator.comparing(CharacterDTO::getLevel))
				.toList();

		dtoCharacters.forEach(System.out::println);

		System.out.println();
		System.out.println("Task #6");
		serializeCollection(archetypeList);
		deserializeCollection("archetypes.bin");

		System.out.println();
		System.out.println("Task #7");
		ForkJoinPool threadPool = new ForkJoinPool(3);

		try {
			threadPool.submit(() -> {
				archetypeList.parallelStream().forEach(archetype -> {
                    System.out.println("Archetype: " + archetype.getName() + " on " + Thread.currentThread().getName());
					archetype.getCharacterList().forEach(character -> {
						try {
							Thread.sleep(2500);
							System.out.println(" Character: " + character.getName() + " " +character.getArchetype().getName() + " on " + Thread.currentThread().getName());
							Thread.sleep(2500);
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
						}
					});
				});
			}).get();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
			try {
				if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
					threadPool.shutdownNow();
				}
			} catch (InterruptedException e) {
				threadPool.shutdownNow();
			}
		}

		System.out.println("Tasks completed");
	}


	public static void serializeCollection(List<Archetype> collection) {
		String fileName = "archetypes.bin";
		try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName))) {
			System.out.println("Collection serialized to file " + fileName);
			stream.writeObject(collection);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deserializeCollection(String fileName) {
		try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName))) {
			List<Archetype> archetypeList = (List<Archetype>) stream.readObject();
			System.out.println(fileName + " deserialized");

			archetypeList.forEach(archetype -> {
				System.out.println("Archetype: " + archetype.getName());
				archetype.getCharacterList().forEach(System.out::println);
			});
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
