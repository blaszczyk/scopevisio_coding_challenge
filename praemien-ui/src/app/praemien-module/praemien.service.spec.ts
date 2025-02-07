import { TestBed } from '@angular/core/testing';

import { PraemienService } from './praemien.service';

describe('PraemienService', () => {
  let service: PraemienService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PraemienService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
