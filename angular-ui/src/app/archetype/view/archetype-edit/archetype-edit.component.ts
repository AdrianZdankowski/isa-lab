import { Component, OnInit } from '@angular/core';
import { ArchetypeForm } from '../../model/archetype-form';
import { ArchetypeService } from '../../service/archetype.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-archetype-edit',
  templateUrl: './archetype-edit.component.html',
  styleUrls: ['./archetype-edit.component.css']
})
export class ArchetypeEditComponent implements OnInit {

  uuid: string | undefined;
  
  archetype: ArchetypeForm | undefined;

  original: ArchetypeForm | undefined;

  constructor(
    private archetypeService: ArchetypeService,
    private route: ActivatedRoute,
    private router: Router
  ){}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.archetypeService.getArchetype(params['uuid'])
      .subscribe(archetype => {
        this.uuid = archetype.id
        this.archetype = {
          name: archetype.name,
          baseHealth: archetype.baseHealth,
          baseDamage: archetype.baseDamage
        };
        this.original = {...this.archetype}
      });
    });
  }


  onSubmit(): void {
    this.archetypeService.patchArchetype(this.uuid!, this.archetype!)
    .subscribe(() => this.router.navigate(['/archetypes']));
  }
}


