package poc.amazons3.controllers.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import poc.amazons3.models.DataItem;

/**
 * Created by 310280812 on 3/31/2017.
 */
@Component("dataItemValidator")
public class DataItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return DataItem.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "The name need to be informed");
        DataItem dataItem = (DataItem) target;
        if (dataItem.getName().startsWith("scale")) {
            errors.rejectValue("name", "scale", "Scale is not accepted.");
        }
    }
}
