import { Routes } from '@angular/router';
import { PraemienAnfrageComponent } from './praemien-module/praemien-anfrage/praemien-anfrage.component';
import { PraemienAnfrageSummaryComponent } from './praemien-module/praemien-anfrage-summary/praemien-anfrage-summary.component';

export const routes: Routes = [
    { path: "", component: PraemienAnfrageComponent },
    { path: "summary/:id", component: PraemienAnfrageSummaryComponent }
];
