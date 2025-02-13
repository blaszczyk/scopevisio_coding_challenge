import { Routes } from '@angular/router';
import { PraemienAntragComponent } from './praemien-module/praemien-antrag/praemien-antrag.component';
import { PraemienAntragSummaryComponent } from './praemien-module/praemien-antrag-summary/praemien-antrag-summary.component';

export const routes: Routes = [
    { path: "", component: PraemienAntragComponent },
    { path: "summary/:id", component: PraemienAntragSummaryComponent }
];
