import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PraemienService } from '../praemien.service';
import { IPraemienAntragSummary } from '../domain';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-praemien-antrag-summary',
  imports: [NgIf],
  templateUrl: './praemien-antrag-summary.component.html',
  styleUrl: './praemien-antrag-summary.component.css'
})
export class PraemienAntragSummaryComponent implements OnInit {

  summary: IPraemienAntragSummary | null = null;

  constructor(private readonly route: ActivatedRoute,
      private readonly praemienService: PraemienService
  ){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id: string = params.get('id')!;
      this.praemienService.getSummary(id).subscribe(summary => {
        this.summary = summary;
      });
    });
  }

}
