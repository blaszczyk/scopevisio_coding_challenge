import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PraemienAntragComponent } from './praemien-antrag/praemien-antrag.component';
@NgModule({
  declarations: [PraemienAntragComponent],
  imports: [
    CommonModule
  ],
  exports: [PraemienAntragComponent],
})
export class PraemienModuleModule { }
