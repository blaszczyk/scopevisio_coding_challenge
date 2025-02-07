import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PraemienAnfrageSummaryComponent } from './praemien-anfrage-summary.component';

describe('PraemienAnfrageSummaryComponent', () => {
  let component: PraemienAnfrageSummaryComponent;
  let fixture: ComponentFixture<PraemienAnfrageSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PraemienAnfrageSummaryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PraemienAnfrageSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
