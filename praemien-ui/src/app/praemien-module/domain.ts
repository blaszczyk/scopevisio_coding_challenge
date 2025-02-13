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

export interface IPraemienAntragRequest extends ILocation {
    kilometerleistung: number;
    fahrzeugtyp: Fahrzeugtyp;
}

export interface IPraemienAntragResponse {
    id: string;
    praemie: number;
}

export interface IPraemienAntragSummary extends IPraemienAntragRequest, IPraemienAntragResponse {}
