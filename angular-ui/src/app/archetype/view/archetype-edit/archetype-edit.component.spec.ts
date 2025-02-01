import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchetypeEditComponent } from './archetype-edit.component';

describe('ArchetypeEditComponent', () => {
  let component: ArchetypeEditComponent;
  let fixture: ComponentFixture<ArchetypeEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ArchetypeEditComponent]
    });
    fixture = TestBed.createComponent(ArchetypeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
