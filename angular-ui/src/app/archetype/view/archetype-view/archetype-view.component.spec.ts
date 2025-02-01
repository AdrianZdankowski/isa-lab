import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchetypeViewComponent } from './archetype-view.component';

describe('ArchetypeViewComponent', () => {
  let component: ArchetypeViewComponent;
  let fixture: ComponentFixture<ArchetypeViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ArchetypeViewComponent]
    });
    fixture = TestBed.createComponent(ArchetypeViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
