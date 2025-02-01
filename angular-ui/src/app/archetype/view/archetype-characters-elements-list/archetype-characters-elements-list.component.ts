import { Component, OnInit } from '@angular/core';
import { Archetype } from '../../model/archetype';
import { CharacterService } from 'src/app/character/service/character.service';
import { ArchetypeService } from '../../service/archetype.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Character } from 'src/app/character/model/character';

@Component({
  selector: 'app-archetype-characters-elements-list',
  templateUrl: './archetype-characters-elements-list.component.html',
  styleUrls: ['./archetype-characters-elements-list.component.css']
})
export class ArchetypeCharactersElementsListComponent implements OnInit {

  archetypesWithDetailsAndCharacters: {
    archetype: Archetype;
    baseHealth: number;
    baseDamage: number;
    characters: Character[];
  }[] = [];
  
  constructor(
    private characterService: CharacterService,
    private archetypeService: ArchetypeService,
    private route: ActivatedRoute,
    private router: Router
  ){}

  ngOnInit(): void {
    this.loadAllArchetypesWithDetailsAndCharacters();
  }

  loadAllArchetypesWithDetailsAndCharacters(): void {
    this.archetypeService.getArchetypes().subscribe({
      next: (archetypes) => {
        archetypes.archetypes.forEach((archetype) => {
          this.archetypeService.getArchetype(archetype.id).subscribe({
            next: (archetypeDetails) => {
              this.characterService.getCharacterByArchetype(archetype.id).subscribe({
                next: (characterList) => {
                  this.archetypesWithDetailsAndCharacters.push({
                    archetype,
                    baseHealth: archetypeDetails.baseHealth,
                    baseDamage: archetypeDetails.baseDamage,
                    characters: characterList.characters,
                  });
                },
                error: (err) => console.error('Failed to load characters:', err),
              });
            },
            error: (err) => console.error('Failed to load archetype details:', err),
          });
        });
      },
      error: (err) => console.error('Failed to load archetypes:', err),
    });
    console.log(this.archetypesWithDetailsAndCharacters)
  }

  onCharacterDelete(uuid: string): void {
    this.characterService.deleteCharacter(uuid).subscribe(() => this.ngOnInit());
  }
}


