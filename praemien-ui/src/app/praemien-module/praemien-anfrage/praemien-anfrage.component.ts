import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatGridListModule } from '@angular/material/grid-list';
import { FormsModule } from '@angular/forms';
import { Fahrzeugtyp, ILocation, IPraemienAnfrageRequest } from '../domain';
import { LocationService } from '../location.service';
import { PraemienService } from '../praemien.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-praemien-anfrage',
  imports: [MatInputModule, MatFormFieldModule, MatSelectModule, MatButtonModule, MatGridListModule, FormsModule],
  templateUrl: './praemien-anfrage.component.html',
  styleUrl: './praemien-anfrage.component.css'
})
export class PraemienAnfrageComponent {

  postleitzahl: string = '';

  locations: ILocation[] = [];

  selectedLocation: ILocation | null = null;

  fahrzeugtypen: Fahrzeugtyp[] = [Fahrzeugtyp.LKW, Fahrzeugtyp.PKW, Fahrzeugtyp.ZWEIRAD];

  selectedFahrzeugtyp: Fahrzeugtyp | null = null;

  kilometerleistung: number | null = null;

  constructor(private readonly locationService: LocationService, 
    private readonly praemienService: PraemienService,
    private readonly router: Router) {}

  changePostleitzahl() {
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

  requestPraemienAnfrage() {
    const selectedLocation = this.selectedLocation!;
    const request: IPraemienAnfrageRequest = {
        bundesland: selectedLocation.bundesland,
        kreis: selectedLocation.kreis,
        stadt: selectedLocation.stadt,
        bezirk: selectedLocation.bezirk,
        postleitzahl: selectedLocation.postleitzahl,
        kilometerleistung: this.kilometerleistung!,
        fahrzeugtyp: this.selectedFahrzeugtyp!
    };
    this.praemienService.postAnfrage(request)
      .subscribe(response => this.router.navigateByUrl('summary/' + response.id));
  }
}
