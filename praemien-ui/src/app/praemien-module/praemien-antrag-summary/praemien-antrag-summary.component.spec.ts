import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PraemienAntragSummaryComponent } from './praemien-antrag-summary.component';

describe('PraemienAntragSummaryComponent', () => {
  let component: PraemienAntragSummaryComponent;
  let fixture: ComponentFixture<PraemienAntragSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PraemienAntragSummaryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PraemienAntragSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
