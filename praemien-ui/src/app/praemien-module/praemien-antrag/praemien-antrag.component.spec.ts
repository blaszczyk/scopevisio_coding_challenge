import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PraemienAntragComponent } from './praemien-antrag.component';

describe('PraemienAntragComponent', () => {
  let component: PraemienAntragComponent;
  let fixture: ComponentFixture<PraemienAntragComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PraemienAntragComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PraemienAntragComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
