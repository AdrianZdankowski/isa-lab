import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { ArchetypeDetails } from '../../model/archetype-details';
import { ArchetypeService } from '../../service/archetype.service';

@Component({
  selector: 'app-archetype-view',
  templateUrl: './archetype-view.component.html',
  styleUrls: ['./archetype-view.component.css']
})
export class ArchetypeViewComponent implements OnInit{

  archetype: ArchetypeDetails | undefined;

  constructor(private service: ArchetypeService, private route: ActivatedRoute, private router: Router) {

  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.service.getArchetype(params['uuid'])
        .subscribe(archetype => this.archetype = archetype)
    });
  }

}


