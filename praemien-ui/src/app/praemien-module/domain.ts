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

export interface IPraemienAntragRequest {
    kilometerleistung: number;
    fahrzeugtyp: Fahrzeugtyp;
    ort: ILocation;
}

export interface IPraemienAntragResponse {
    id: string;
    praemie: number;
}

export interface IPraemienAntragSummary extends IPraemienAntragRequest, IPraemienAntragResponse {}
