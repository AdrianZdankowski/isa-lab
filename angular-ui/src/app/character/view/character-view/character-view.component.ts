import { Component, OnInit } from '@angular/core';
import { CharacterService } from "../../service/character.service";
import { ActivatedRoute, Router } from "@angular/router";
import { CharacterDetails } from "../../model/character-details";
import { ArchetypeService } from 'src/app/archetype/service/archetype.service';

@Component({
  selector: 'app-character-view',
  templateUrl: './character-view.component.html',
  styleUrls: ['./character-view.component.css']
})
export class CharacterViewComponent implements OnInit {

  character: CharacterDetails | undefined;

  constructor(
    private service: CharacterService, 
    private route: ActivatedRoute, 
    private router: Router,
    private archetypeService: ArchetypeService
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.service.getCharacter(params['uuid'])
        .subscribe(character => {
          this.character = character;

          this.archetypeService.getArchetype(character.archetypeInfo.id)
          .subscribe(archetype => {
            this.character!.archetypeName = archetype.name;  
          })
        })
    });

    
    
  }

}
