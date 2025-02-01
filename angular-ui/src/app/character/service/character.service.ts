import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Characters } from "../model/characters";
import { CharacterDetails } from "../model/character-details";
import { CharacterForm } from "../model/character-form";
import { CharacterByArchetype } from '../model/character-by-archetype';

@Injectable()
export class CharacterService {

  constructor(private http: HttpClient) {

  }

  getCharacters(): Observable<Characters> {
    return this.http.get<Characters>('/api/characters');
  }

  getCharacter(uuid: string): Observable<CharacterDetails> {
    return this.http.get<CharacterDetails>('/api/characters/' + uuid);
  }

  deleteCharacter(uuid: string): Observable<any> {
    return this.http.delete('/api/characters/' + uuid);
  }

  patchCharacter(uuid: string, request: CharacterForm): Observable<any> {
    return this.http.patch('/api/characters/' + uuid, request);
  }

  getCharacterByArchetype(uuid: string): Observable<any> {
    return this.http.get<CharacterByArchetype[]>('/api/archetypes/' + uuid + '/characters');
  }

  postCharacter(request: CharacterForm): Observable<any> {
    return this.http.post('/api/characters', request);
  }
}
