import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArchetypeListComponent } from './archetype/view/archetype-list/archetype-list.component';
import { CharacterListComponent } from "./character/view/character-list/character-list.component";
import { CharacterViewComponent } from "./character/view/character-view/character-view.component";
import { CharacterEditComponent } from "./character/view/character-edit/character-edit.component";
import { ArchetypeViewComponent } from './archetype/view/archetype-view/archetype-view.component';
import { ArchetypeCreateComponent } from './archetype/view/archetype-create/archetype-create.component';
import { ArchetypeEditComponent } from './archetype/view/archetype-edit/archetype-edit.component';
import { ArchetypeCharactersElementsListComponent } from './archetype/view/archetype-characters-elements-list/archetype-characters-elements-list.component';
import { CharacterCreateComponent } from './character/view/character-create/character-create.component';


const routes: Routes = [
  {
    component: ArchetypeCharactersElementsListComponent,
    path: "information"
  },
  {
    component: ArchetypeListComponent,
    path: "archetypes"
  },
  {
    component: ArchetypeCreateComponent,
    path: "archetypes/create",
  },
  {
    component: ArchetypeViewComponent,
    path: "archetypes/:uuid"
  },
  {
    component: ArchetypeEditComponent,
    path: "archetypes/:uuid/edit"
  },
  {
    component: CharacterListComponent,
    path: "characters"
  },
  {
    component: CharacterViewComponent,
    path: "characters/:uuid"
  },
  {
    component: CharacterEditComponent,
    path: "characters/:uuid/:archetypeUuid/edit"
  },
  {
    component: CharacterCreateComponent,
    path: "characters/:uuid/create"
  }

  
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {

}
