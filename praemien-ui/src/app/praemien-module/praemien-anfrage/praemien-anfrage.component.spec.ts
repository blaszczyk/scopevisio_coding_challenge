import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PraemienAnfrageComponent } from './praemien-anfrage.component';

describe('PraemienAnfrageComponent', () => {
  let component: PraemienAnfrageComponent;
  let fixture: ComponentFixture<PraemienAnfrageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PraemienAnfrageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PraemienAnfrageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
