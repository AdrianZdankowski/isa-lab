import { Archetype } from "src/app/archetype/model/archetype";

export interface CharacterDetails {
  id: string;

  name: string;
  
  powerLevel: number;

  archetypeInfo: {
    id: string;
  }

  archetypeName?: string;

}
