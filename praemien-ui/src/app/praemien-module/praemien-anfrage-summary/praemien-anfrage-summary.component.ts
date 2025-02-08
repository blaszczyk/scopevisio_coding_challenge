import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PraemienService } from '../praemien.service';
import { IPraemienAnfrageSummary } from '../domain';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-praemien-anfrage-summary',
  imports: [NgIf],
  templateUrl: './praemien-anfrage-summary.component.html',
  styleUrl: './praemien-anfrage-summary.component.css'
})
export class PraemienAnfrageSummaryComponent implements OnInit {

  summary: IPraemienAnfrageSummary | null = null;

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
