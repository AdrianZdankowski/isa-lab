import { Component, OnInit } from '@angular/core';
import { CharacterService } from '../../service/character.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CharacterForm } from '../../model/character-form';
import { ArchetypeService } from 'src/app/archetype/service/archetype.service';
import { Archetypes } from 'src/app/archetype/model/archetypes';

@Component({
  selector: 'app-character-edit',
  templateUrl: './character-edit.component.html',
  styleUrls: ['./character-edit.component.css']
})
export class CharacterEditComponent implements OnInit {

  uuid: string | undefined;

  character: CharacterForm | undefined;

  original: CharacterForm | undefined;

  archetypes: Archetypes | undefined;

  constructor(
    private characterService: CharacterService,
    private archetypeService: ArchetypeService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  // ngOnInit() {
  //   this.route.params.subscribe(params => {
  //     this.archetypeService.getArchetypes()
  //       .subscribe(archetype => this.archetypes = archetype);

  //     this.characterService.getCharacter(params['uuid'])
  //       .subscribe(character => {
  //         this.uuid = character.id;
  //         this.character = {
  //           name: character.name,
  //           powerLevel: character.powerLevel,
  //           archetypeId: character.archetypeInfo.id
  //         };
  //         this.original = {...this.character};
  //       });
  //   });

  //   console.log("Original: ", this.original)
    
  // }

  ngOnInit(): void {
    const archetypeId = this.route.snapshot.paramMap.get('archetypeUuid');
    const characterId = this.route.snapshot.paramMap.get('uuid');
    
    if (characterId && archetypeId) {
      this.characterService.getCharacter(characterId)
      .subscribe(character => {
        this.uuid = character.id;
        this.character = {
          name: character.name,
          powerLevel: character.powerLevel,
          archetypeId: archetypeId
        };
      });
      console.log(this.character);
    }
  }

  onSubmit(): void {
    console.log(this.character)
    this.characterService.patchCharacter(this.uuid!, this.character!)
      .subscribe(() => this.router.navigate(['/characters']));
  }

}
