import { Component, OnInit } from '@angular/core';
import { CharacterForm } from '../../model/character-form';
import { CharacterService } from '../../service/character.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-character-create',
  templateUrl: './character-create.component.html',
  styleUrls: ['./character-create.component.css']
})
export class CharacterCreateComponent implements OnInit {

  character: CharacterForm = {
    name: '',
    powerLevel: 0,
    archetypeId: ''
  };

  constructor(
    private characterService: CharacterService,
    private route: ActivatedRoute,
    private router: Router
  ){}

  ngOnInit(): void {
    const archetypeId = this.route.snapshot.paramMap.get('uuid');
    if (archetypeId) {
      this.character.archetypeId = archetypeId;
    }
  }

  onSubmit(): void {
    console.log(this.character);
    this.characterService.postCharacter(this.character)
    .subscribe({
      next: () => this.router.navigate(['/information']),
      error: (err) => console.log('Error while sending data:', err)
    });
  }
  
}
