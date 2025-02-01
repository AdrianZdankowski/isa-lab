import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CharacterViewComponent } from './character-view.component';

describe('CharacterViewComponent', () => {
  let component: CharacterViewComponent;
  let fixture: ComponentFixture<CharacterViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CharacterViewComponent]
    });
    fixture = TestBed.createComponent(CharacterViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
