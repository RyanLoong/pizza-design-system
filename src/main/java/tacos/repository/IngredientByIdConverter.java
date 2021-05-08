package tacos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.entity.Ingredient;

import java.util.Optional;

/**
 * @author RyanLoong
 * @Date 2021/5/7 9:43
 * @Classname IngredientByIdConverter
 * @Description 构造一个从id到对象的转换器
 */
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {


    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String id) {
        Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
        return optionalIngredient.isPresent() ?
                optionalIngredient.get() : null;
    }
}
