import { Component, OnInit } from '@angular/core';
import { CharacterService } from "../../service/character.service";
import { Characters } from "../../model/characters";
import { Character } from "../../model/character";

@Component({
  selector: 'app-character-list',
  templateUrl: './character-list.component.html',
  styleUrls: ['./character-list.component.css']
})
export class CharacterListComponent implements OnInit{

  constructor(private service: CharacterService) {
  }

  characters: Characters | undefined;

  ngOnInit(): void {
    this.service.getCharacters().subscribe(characters => this.characters = characters);
  }

  onDelete(character: Character): void {
    this.service.deleteCharacter(character.id).subscribe(() => this.ngOnInit());
  }

}
