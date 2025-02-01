import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchetypeCharactersElementsListComponent } from './archetype-characters-elements-list.component';

describe('ArchetypeCharactersElementsListComponent', () => {
  let component: ArchetypeCharactersElementsListComponent;
  let fixture: ComponentFixture<ArchetypeCharactersElementsListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ArchetypeCharactersElementsListComponent]
    });
    fixture = TestBed.createComponent(ArchetypeCharactersElementsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
