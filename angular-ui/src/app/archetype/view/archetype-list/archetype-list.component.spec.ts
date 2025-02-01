import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchetypeListComponent } from './archetype-list.component';

describe('ArchetypeListComponent', () => {
  let component: ArchetypeListComponent;
  let fixture: ComponentFixture<ArchetypeListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ArchetypeListComponent]
    });
    fixture = TestBed.createComponent(ArchetypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
