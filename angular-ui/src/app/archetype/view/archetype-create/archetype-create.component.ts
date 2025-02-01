import { Component } from '@angular/core';
import { ArchetypeService } from '../../service/archetype.service';
import { ArchetypeForm } from '../../model/archetype-form';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-archetype-create',
  templateUrl: './archetype-create.component.html',
  styleUrls: ['./archetype-create.component.css']
})
export class ArchetypeCreateComponent {

  archetype: ArchetypeForm = {
    name: '',
    baseHealth: 0,
    baseDamage: 0
  };

  constructor(
    private archetypeService: ArchetypeService,
    private route: ActivatedRoute,
    private router: Router
  ){}

 
  onSubmit(): void {
    if (this.archetype) {
      this.archetypeService.postArchetype(this.archetype)
        .subscribe({
          next: () => this.router.navigate(['/archetypes']),
          error: (err) => console.error('Error while sending data:', err)
        });
    }
  }
}
