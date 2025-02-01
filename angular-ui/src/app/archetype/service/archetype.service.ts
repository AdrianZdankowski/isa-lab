import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Archetypes } from "../model/archetypes";
import { ArchetypeDetails } from '../model/archetype-details';
import { ArchetypeForm } from '../model/archetype-form';
// import { CharacterForm } from "../model/character-form";

@Injectable()
export class ArchetypeService {

  constructor(private http: HttpClient) {

  }

  getArchetypes(): Observable<Archetypes> {
    return this.http.get<Archetypes>('/api/archetypes');
  }

  getArchetype(uuid: string): Observable<ArchetypeDetails> {
    return this.http.get<ArchetypeDetails>('/api/archetypes/' + uuid);
  }

  deleteArchetype(uuid: string): Observable<any> {
    return this.http.delete('/api/archetypes/' + uuid);
  }
  
  patchArchetype(uuid: string, request: ArchetypeForm): Observable<any> {
    return this.http.patch('/api/archetypes/' + uuid, request);
  }

  postArchetype(request: ArchetypeForm): Observable<any> {
    return this.http.post('/api/archetypes', request);
  }
}
