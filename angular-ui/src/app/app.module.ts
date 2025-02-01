import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './component/footer/footer.component';
import { HeaderComponent } from './component/header/header.component';
import { NavComponent } from './component/nav/nav.component';
import { MainComponent } from './component/main/main.component';
import { HttpClientModule } from "@angular/common/http";
import { CharacterListComponent } from './character/view/character-list/character-list.component';
import { CharacterService } from './character/service/character.service';
import { CharacterViewComponent } from './character/view/character-view/character-view.component';
import { CharacterEditComponent } from './character/view/character-edit/character-edit.component';
import { FormsModule } from "@angular/forms";
import { ArchetypeListComponent } from './archetype/view/archetype-list/archetype-list.component';
import { ArchetypeService } from './archetype/service/archetype.service';
import { ArchetypeViewComponent } from './archetype/view/archetype-view/archetype-view.component';
import { ArchetypeCreateComponent } from './archetype/view/archetype-create/archetype-create.component';
import { ArchetypeEditComponent } from './archetype/view/archetype-edit/archetype-edit.component';
import { ArchetypeCharactersElementsListComponent } from './archetype/view/archetype-characters-elements-list/archetype-characters-elements-list.component';
import { CharacterCreateComponent } from './character/view/character-create/character-create.component';

/**
 * Application main module.
 */
@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    NavComponent,
    MainComponent,
    CharacterListComponent,
    CharacterViewComponent,
    CharacterEditComponent,
    ArchetypeListComponent,
    ArchetypeViewComponent,
    ArchetypeCreateComponent,
    ArchetypeEditComponent,
    ArchetypeCharactersElementsListComponent,
    CharacterCreateComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    CharacterService,
    ArchetypeService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {

}
