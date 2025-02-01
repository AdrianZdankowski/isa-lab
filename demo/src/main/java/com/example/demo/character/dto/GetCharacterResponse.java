package com.example.demo.character.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCharacterResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class ArchetypeInfo {
        private UUID id;
        private String name;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class NatureElementInfo {
        private UUID id;
        private String name;
    }

    private UUID id;

    private String name;

    private int powerLevel;

    private ArchetypeInfo archetypeInfo;

    private NatureElementInfo elementInfo;
}
