export interface ILocation {
    bundesland: string;
    kreis: string;
    stadt: string;
    postleitzahl: string;
    bezirk: string
}

export enum Fahrzeugtyp {
    LKW = 'LKW',
    PKW = 'PKW',
    ZWEIRAD = 'ZWEIRAD'
}

export interface IPraemienAnfrageRequest extends ILocation {
    kilometerleistung: number;
    fahrzeugtyp: Fahrzeugtyp;
}

export interface IPraemienAnfrageResponse {
    id: string;
    praemie: number;
}
