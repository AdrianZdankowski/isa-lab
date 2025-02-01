package com.example.demo.element.initialize;

import com.example.demo.element.entity.NatureElement;
import com.example.demo.element.service.NatureElementService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {

    private final NatureElementService natureElementService;

    @Autowired
    public InitializeData(NatureElementService natureElementService) {
        this.natureElementService = natureElementService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (natureElementService.getAllNatureElements().isEmpty()) {

            NatureElement solar = NatureElement.builder()
                    .id(UUID.fromString("83bdf2ce-ae92-428f-bda3-d5838053e2b7"))
                    .elementName("Solar")
                    .elementSide("Light")
                    .build();

            NatureElement arc = NatureElement.builder()
                    .id(UUID.fromString("2044fbd5-91e9-41c2-ad9f-6d806eb9eaf4"))
                    .elementName("Arc")
                    .elementSide("Light")
                    .build();

            NatureElement voidElement = NatureElement.builder()
                    .id(UUID.fromString("24e1822a-35ef-415c-9720-0cb3e1737b82"))
                    .elementName("Void")
                    .elementSide("Light")
                    .build();

            NatureElement stasis = NatureElement.builder()
                    .id(UUID.fromString("d1f24efd-e0b1-4c39-9d8d-a2511bbd4742"))
                    .elementName("Stasis")
                    .elementSide("Darkness")
                    .build();

            natureElementService.saveNatureElement(solar);
            natureElementService.saveNatureElement(arc);
            natureElementService.saveNatureElement(voidElement);
            natureElementService.saveNatureElement(stasis);

        }
    }
}
