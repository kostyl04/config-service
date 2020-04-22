package com.kostylenko.config_service.config_service_rest.domain.service.parser;

import com.kostylenko.config_service.config_service_rest.domain.service.exception.TypeFieldParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.kostylenko.config_service.config_service_rest.domain.model.Type.LIST;

@Slf4j
@Component
public class ListTypeFieldParser extends AbstractTypeFieldParser<List> {

    public ListTypeFieldParser() {
        super(LIST);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List parse(Object value) throws TypeFieldParseException {
        if (!(value instanceof List)) {
            throw getTypeFieldParseException(value);
        }
        List tempList = (List) value;
        List<String> result = new ArrayList<>();
        tempList.forEach(val -> result.add(val.toString()));
        return result;
    }
}
