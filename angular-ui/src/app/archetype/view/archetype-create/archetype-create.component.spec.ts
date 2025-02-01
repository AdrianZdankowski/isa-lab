import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchetypeCreateComponent } from './archetype-create.component';

describe('ArchetypeCreateComponent', () => {
  let component: ArchetypeCreateComponent;
  let fixture: ComponentFixture<ArchetypeCreateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ArchetypeCreateComponent]
    });
    fixture = TestBed.createComponent(ArchetypeCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
