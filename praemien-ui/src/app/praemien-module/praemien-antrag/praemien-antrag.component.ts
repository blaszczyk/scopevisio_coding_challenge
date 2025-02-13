import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatGridListModule } from '@angular/material/grid-list';
import { FormsModule } from '@angular/forms';
import { Fahrzeugtyp, ILocation, IPraemienAntragRequest } from '../domain';
import { LocationService } from '../location.service';
import { PraemienService } from '../praemien.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-praemien-antrag',
  imports: [MatInputModule, MatFormFieldModule, MatSelectModule, MatButtonModule, MatGridListModule, FormsModule],
  templateUrl: './praemien-antrag.component.html',
  styleUrl: './praemien-antrag.component.css'
})
export class PraemienAntragComponent {

  postleitzahl: string = '';

  locations: ILocation[] = [];

  selectedLocation: ILocation | null = null;

  fahrzeugtypen: { id:Fahrzeugtyp, displayName: string }[] = [
    { id: Fahrzeugtyp.LKW, displayName: 'LKW' }, 
    { id: Fahrzeugtyp.PKW, displayName: 'PKW' },
    { id: Fahrzeugtyp.ZWEIRAD, displayName: 'Zweirad' },
  ];

  selectedFahrzeugtyp: Fahrzeugtyp | null = null;

  kilometerleistung: number | null = null;

  constructor(private readonly locationService: LocationService, 
    private readonly praemienService: PraemienService,
    private readonly router: Router) {}

  updateLocations() {
    const regex = /[0-9]{5}/;
    this.selectedLocation = null;
    if ( regex.test(this.postleitzahl) ) {
      this.locationService.getLocations(this.postleitzahl)
        .subscribe(locations => {
          this.locations = locations;
          if (locations.length === 1) {
            this.selectedLocation = locations[0];
          }
        });
    }
    else {
      this.locations = [];
    }
  }

  inputValid(): boolean {
    return !!(this.selectedLocation && this.selectedFahrzeugtyp && this.kilometerleistung && this.kilometerleistung > 0);
  }

  requestPraemienAntrag() {
    const selectedLocation = this.selectedLocation!;
    const request: IPraemienAntragRequest = {
        bundesland: selectedLocation.bundesland,
        kreis: selectedLocation.kreis,
        stadt: selectedLocation.stadt,
        bezirk: selectedLocation.bezirk,
        postleitzahl: selectedLocation.postleitzahl,
        kilometerleistung: this.kilometerleistung!,
        fahrzeugtyp: this.selectedFahrzeugtyp!
    };
    this.praemienService.postAntrag(request)
      .subscribe(response => this.router.navigateByUrl('summary/' + response.id));
  }
}
