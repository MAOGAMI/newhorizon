package pl.mkpk.newhorizon;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mkpk.newhorizon.cookbook.CookingRecipe;
import pl.mkpk.newhorizon.cookbook.IngedientsBase;
import pl.mkpk.newhorizon.cookbook.Ingredient;
import pl.mkpk.newhorizon.cookbook.StackType;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class CookingRecipeTest {

    @Test
    public void CreateRecite(){
        //given
        IngedientsBase ingedientsBase= new IngedientsBase();
        ingedientsBase.addIngredient(new Ingredient(
                "Jajka",
                new BigDecimal(65),
                StackType.UNIT,
                true,
                new BigDecimal(180))
        );
        ingedientsBase.addIngredient(new Ingredient(
                "Mleko",
                new BigDecimal(100),
                StackType.ML,
                true,
                new BigDecimal(65))
        );
        ingedientsBase.addIngredient(new Ingredient(
                "Mąka",
                new BigDecimal(100),
                StackType.GRAMS,
                true,
                new BigDecimal(18))
        );

        // when
        CookingRecipe recipe= new CookingRecipe.RecipeBuilder(ingedientsBase)
                .setName("test recipe")
                .addIngredients( "Jajka",new BigDecimal(1))
                .addIngredients( "Mleko",new BigDecimal(100))
                .addIngredients( "Mąka",new BigDecimal(200))
                .addPreparationStep("Wbij jajko do mąki",new BigDecimal(1))
                .addPreparationStep("Wlej mleko",new BigDecimal(1))
                .addPreparationStep("Wymieszaj",new BigDecimal(5))
                .build();

        //then
        System.out.println(recipe.toString());
    }
}
