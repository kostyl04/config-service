package com.kostylenko.config_service.config_service_rest.domain.service;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.common.common_http.exception.NotFoundApiException;
import com.kostylenko.config_service.config_service_rest.data.repository.MetaRepository;
import com.kostylenko.config_service.config_service_rest.domain.model.Field;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import com.kostylenko.config_service.config_service_rest.domain.service.validator.MetaValidator;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static com.kostylenko.config_service.config_service_rest.domain.model.Type.STRING;
import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.*;
import static com.kostylenko.config_service.config_service_rest.util.MapperUtil.MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@TestInstance(PER_CLASS)
class MetaServiceTest {

    private MetaService metaService;
    private Meta meta;

    @BeforeAll
    void init() {
        metaService = new MetaService(mockMetaRepository(), new MetaValidator(), MAPPER);
    }

    @BeforeEach
    void initMeta() {
        meta = createMeta();
    }

    Meta createMeta() {
        meta = new Meta();
        meta.setName("someMeta");
        Field field = new Field();
        field.setNullable(true);
        field.setType(STRING);
        field.setKey(true);
        field.setName("fieldName");
        meta.setFields(Set.of(field));
        meta.setKeyDelimiter(":");
        return meta;
    }

    private MetaRepository mockMetaRepository() {
        MetaRepository metaRepository = Mockito.mock(MetaRepository.class);
        when(metaRepository.existsByName(eq("existingMeta"))).thenReturn(true);
        var dataMeta = MAPPER.map(createMeta(), com.kostylenko.config_service.config_service_rest.data.entity.Meta.class);
        when(metaRepository.save(eq(dataMeta))).then(a -> {
            var map = MAPPER.map(meta, com.kostylenko.config_service.config_service_rest.data.entity.Meta.class);
            map.setId("newId");
            return map;
        });
        when(metaRepository.findByName(eq("notExistingMeta"))).thenReturn(null);
        when(metaRepository.findByName(eq("someMeta"))).thenReturn(dataMeta);
        return metaRepository;
    }

    @Nested
    class CreateMeta {

        @Test
        void should_throw_exception_when_meta_does_not_have_key_field() {
            meta.getFields().iterator().next().setKey(false);
            BadRequestApiException exception = assertThrows(BadRequestApiException.class, () -> metaService.createMeta(meta));
            assertEquals(KEY_FIELD_NOT_FOUND, exception.getCode());
        }

        @Test
        void should_throw_exception_when_meta_already_exists() {
            meta.setName("existingMeta");
            BadRequestApiException exception = assertThrows(BadRequestApiException.class, () -> metaService.createMeta(meta));
            assertEquals(META_ALREADY_EXISTS, exception.getCode());
        }

        @Test
        void should_throw_exception_when_meta_has_empty_fields() {
            meta.setFields(new HashSet<>());
            BadRequestApiException exception = assertThrows(BadRequestApiException.class, () -> metaService.createMeta(meta));
            assertEquals(EMPTY_META_FIELDS, exception.getCode());
        }

        @Test
        void should_throw_exception_when_meta_has_null_fields() {
            meta.setFields(null);
            BadRequestApiException exception = assertThrows(BadRequestApiException.class, () -> metaService.createMeta(meta));
            assertEquals(EMPTY_META_FIELDS, exception.getCode());
        }

        @Test
        void should_complete_method_when_meta_is_valid() {
            Meta savedMeta = metaService.createMeta(meta);
            meta.setId(savedMeta.getId());
            assertEquals(meta, savedMeta);
        }
    }

    @Nested
    class GetMeta {

        @Test
        void should_throw_exception_when_meta_not_found() {
            meta.setName("notExistingMeta");
            NotFoundApiException notFoundApiException = assertThrows(NotFoundApiException.class, () -> metaService.getMeta(meta.getName()));
            assertEquals(META_NOT_FOUND, notFoundApiException.getCode());
        }

        @Test
        void should_complete_method_when_meta_exists() {
            Meta existingMeta = metaService.getMeta("someMeta");
            assertEquals("someMeta", existingMeta.getName());
        }
    }


}
