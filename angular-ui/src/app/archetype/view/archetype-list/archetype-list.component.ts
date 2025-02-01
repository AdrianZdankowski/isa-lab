import { Component, OnInit } from '@angular/core';
import { ArchetypeService } from '../../service/archetype.service';
import { Archetypes } from '../../model/archetypes';
import { Archetype } from '../../model/archetype';

@Component({
  selector: 'app-archetype-list',
  templateUrl: './archetype-list.component.html',
  styleUrls: ['./archetype-list.component.css']
})
export class ArchetypeListComponent implements OnInit{
  constructor(private service: ArchetypeService) {

  }

  archetypes: Archetypes | undefined;

  ngOnInit(): void {
    this.service.getArchetypes().subscribe(archetypes => this.archetypes = archetypes);
  }

  onDelete(archetype: Archetype): void {
    this.service.deleteArchetype(archetype.id).subscribe(() => this.ngOnInit());
  }
}



